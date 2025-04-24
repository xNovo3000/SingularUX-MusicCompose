package org.singularux.music.feature.playback.model

data class PlaybackInfo(
    val title: String,
    val artistName: String,
    val albumArtUri: String,
    val isPlaying: Boolean
)