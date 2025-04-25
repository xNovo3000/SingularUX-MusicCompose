package org.singularux.music.feature.playback.model

import android.net.Uri

data class PlaybackInfo(
    val title: String,
    val artistName: String,
    val albumArtUri: Uri?,
    val isPlaying: Boolean
)