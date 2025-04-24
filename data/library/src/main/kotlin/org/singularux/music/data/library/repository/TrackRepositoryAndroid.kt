package org.singularux.music.data.library.repository

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.singularux.music.core.permission.MusicPermission
import org.singularux.music.core.permission.MusicPermissionManager
import org.singularux.music.data.library.entity.TrackEntity
import org.singularux.music.data.library.util.ArtworkUriRetriever26
import org.singularux.music.data.library.util.ArtworkUriRetriever29
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class TrackRepositoryAndroid @Inject constructor(
    @ApplicationContext private val context: Context,
    private val musicPermissionManager: MusicPermissionManager,
    private val albumRepository: AlbumRepository
) : TrackRepository {

    companion object {

        private const val TAG = "TrackRepositoryAndroid"
        val URI: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        private val GET_ALL_PROJECTION = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION
        )
        private const val GET_ALL_SELECTION =
            "${MediaStore.Audio.Media.IS_MUSIC} = ? AND ${MediaStore.Audio.Media.IS_TRASHED} = ?"
        private val GET_ALL_SELECTION_ARGS = arrayOf("1", "0")
        private const val GET_ALL_SORT_ORDER = MediaStore.Audio.Media.DEFAULT_SORT_ORDER

    }

    override suspend fun getAll(): List<TrackEntity> {
        // Check for permission
        if (!musicPermissionManager.hasPermission(MusicPermission.READ_MUSIC)) {
            Log.d(TAG, "getAll(): permission READ_MUSIC is missing")
            return emptyList()
        }
        // Bootstrap artwork retriever
        val artworkUriRetriever = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ArtworkUriRetriever29()
        } else {
            ArtworkUriRetriever26()
        }
        artworkUriRetriever.initialize(context = context, albumRepository = albumRepository)
        // Query Android
        return withContext(Dispatchers.IO) {
            context.contentResolver.query(
                URI,
                GET_ALL_PROJECTION,
                GET_ALL_SELECTION,
                GET_ALL_SELECTION_ARGS,
                GET_ALL_SORT_ORDER
            ).use { cursor ->
                withContext(Dispatchers.Default) {
                    val result = mutableListOf<TrackEntity>()
                    while (cursor?.moveToNext() == true) {
                        val albumId = cursor.getIntOrNull(3)
                        result.add(
                            element = TrackEntity(
                                id = cursor.getInt(0),
                                title = cursor.getStringOrNull(2) ?: cursor.getString(1),
                                albumId = albumId,
                                artworkUri = artworkUriRetriever[albumId],
                                artistId = cursor.getIntOrNull(4),
                                artistName = cursor.getStringOrNull(5),
                                duration = cursor.getInt(6).milliseconds
                            )
                        )
                    }
                    result
                }
            }
        }
    }

}