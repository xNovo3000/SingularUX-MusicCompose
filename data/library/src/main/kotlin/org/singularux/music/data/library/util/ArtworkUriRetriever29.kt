package org.singularux.music.data.library.util

import android.content.Context
import android.provider.MediaStore
import org.singularux.music.data.library.repository.AlbumRepository

class ArtworkUriRetriever29 : ArtworkUriRetriever {

    override suspend fun initialize(context: Context, albumRepository: AlbumRepository) {}

    override fun get(albumId: Int?): String? {
        return albumId?.let {
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
                .buildUpon()
                .appendPath(it.toString())
                .build()
                .toString()
        }
    }

}