package org.singularux.music.data.library.util

import android.content.Context
import android.provider.MediaStore
import androidx.core.database.getStringOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.singularux.music.data.library.repository.AlbumRepository

class ArtworkUriRetriever26 : ArtworkUriRetriever {

    companion object {
        private val ARTWORK_CACHE_26_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        private val ARTWORK_CACHE_26_PROJECTION = arrayOf(
            MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART
        )
    }

    val cache = mutableMapOf<Int, String?>()

    override suspend fun initialize(context: Context, albumRepository: AlbumRepository) {
        withContext(Dispatchers.IO) {
            context.contentResolver.query(
                ARTWORK_CACHE_26_URI,
                ARTWORK_CACHE_26_PROJECTION,
                null,
                null,
                null
            ).use { cursor ->
                withContext(Dispatchers.Default) {
                    while (cursor?.moveToNext() == true) {
                        val artworkUri = cursor.getStringOrNull(1)
                        if (artworkUri != null) {
                            val id = cursor.getInt(0)
                            cache[id] = artworkUri
                        }
                    }
                }
            }
        }
    }

    override fun get(albumId: Int?): String? {
        return albumId?.let { cache[it] }
    }

}