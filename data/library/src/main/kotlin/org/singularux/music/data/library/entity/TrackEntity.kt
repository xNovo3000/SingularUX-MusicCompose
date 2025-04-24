package org.singularux.music.data.library.entity

import kotlin.time.Duration

data class TrackEntity(
    val id: Int,
    val title: String,
    val albumId: Int?,
    val artworkUri: String?,
    val artistId: Int?,
    val artistName: String?,
    val duration: Duration
)