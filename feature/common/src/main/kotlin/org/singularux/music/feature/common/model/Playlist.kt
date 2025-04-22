package org.singularux.music.feature.common.model

data class Playlist(
    val id: Int,
    val title: String,
    val albumArtUri: String?,
    val numberOfTracks: Int
)