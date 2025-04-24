package org.singularux.music.data.library.repository

import org.singularux.music.data.library.entity.ArtistEntity

interface ArtistRepository {
    suspend fun getAll(): List<ArtistEntity>
}