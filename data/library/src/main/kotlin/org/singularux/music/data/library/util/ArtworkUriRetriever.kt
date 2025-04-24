package org.singularux.music.data.library.util

import android.content.Context
import org.singularux.music.data.library.repository.AlbumRepository

interface ArtworkUriRetriever {
    suspend fun initialize(context: Context, albumRepository: AlbumRepository)
    operator fun get(albumId: Int?): String?
}