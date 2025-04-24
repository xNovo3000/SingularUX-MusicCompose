package org.singularux.music.data.library.entity

data class AlbumEntity(
    val id: Int,
    val title: String,
    val artworkUri: String?,
    val numberOfTracks: Int
)