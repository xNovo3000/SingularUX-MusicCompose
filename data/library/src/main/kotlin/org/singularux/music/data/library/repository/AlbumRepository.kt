package org.singularux.music.data.library.repository

import org.singularux.music.data.library.entity.AlbumEntity

interface AlbumRepository {
    suspend fun getAll(): List<AlbumEntity>
}