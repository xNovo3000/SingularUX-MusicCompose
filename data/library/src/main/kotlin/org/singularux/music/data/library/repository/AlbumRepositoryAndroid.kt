package org.singularux.music.data.library.repository

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.singularux.music.core.permission.MusicPermission
import org.singularux.music.core.permission.MusicPermissionManager
import org.singularux.music.data.library.entity.AlbumEntity
import javax.inject.Inject

class AlbumRepositoryAndroid @Inject constructor(
    @ApplicationContext private val context: Context,
    private val musicPermissionManager: MusicPermissionManager
) : AlbumRepository {

    companion object {

        private const val TAG = "AlbumRepositoryAndroid"
        val URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI

        private val GET_ALL_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        private val GET_ALL_PROJECTION = arrayOf(
            MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS
        )
        private const val GET_ALL_SORT_ORDER = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER

    }

    override suspend fun getAll(): List<AlbumEntity> {
        // Check for permission
        if (!musicPermissionManager.hasPermission(MusicPermission.READ_MUSIC)) {
            Log.d(TAG, "getAll(): permission READ_MUSIC is missing")
            return emptyList()
        }
        // Query Android
        return withContext(Dispatchers.IO) {
            context.contentResolver.query(
                GET_ALL_URI,
                GET_ALL_PROJECTION,
                null,
                null,
                GET_ALL_SORT_ORDER
            ).use { cursor ->
                withContext(Dispatchers.Default) {
                    val result = mutableListOf<AlbumEntity>()
                    while (cursor?.moveToNext() == true) {
                        result.add(
                            element = AlbumEntity(
                                id = cursor.getInt(0),
                                title = cursor.getString(1),
                                numberOfTracks = cursor.getInt(2)
                            )
                        )
                    }
                    result
                }
            }
        }
    }

}