package org.singularux.music.data.library.repository

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.singularux.music.core.permission.MusicPermission
import org.singularux.music.core.permission.MusicPermissionManager
import org.singularux.music.data.library.entity.ArtistEntity
import javax.inject.Inject

class ArtistRepositoryAndroid @Inject constructor(
    @ApplicationContext private val context: Context,
    private val musicPermissionManager: MusicPermissionManager
) : ArtistRepository {

    companion object {

        private const val TAG = "ArtistRepositoryAndroid"

        private val GET_ALL_URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
        private val GET_ALL_PROJECTION = arrayOf(
            MediaStore.Audio.Artists._ID, MediaStore.Audio.Artists.ARTIST
        )
        private const val GET_ALL_SORT_ORDER = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER

    }

    override suspend fun getAll(): List<ArtistEntity> {
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
                    val result = mutableListOf<ArtistEntity>()
                    while (cursor?.moveToNext() == true) {
                        result.add(
                            element = ArtistEntity(
                                id = cursor.getInt(0),
                                name = cursor.getString(1)
                            )
                        )
                    }
                    result
                }
            }
        }
    }

}