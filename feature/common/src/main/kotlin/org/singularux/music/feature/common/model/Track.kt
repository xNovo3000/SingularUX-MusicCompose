package org.singularux.music.feature.common.model

import kotlin.time.Duration

data class Track(
    val id: Int,
    val title: String,
    val artistName: String,
    val artistId: Int,
    val albumName: String,
    val albumId: Int,
    val albumArtUri: String?,
    val duration: Duration
)