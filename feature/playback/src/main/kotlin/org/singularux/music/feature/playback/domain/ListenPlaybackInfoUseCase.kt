package org.singularux.music.feature.playback.domain

import android.util.Log
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import org.singularux.music.feature.playback.foreground.MusicControllerFacade
import org.singularux.music.feature.playback.model.PlaybackInfo
import javax.inject.Inject

@ActivityRetainedScoped
class ListenPlaybackInfoUseCase @Inject constructor(
    private val musicControllerFacade: MusicControllerFacade
) {

    companion object {
        private const val TAG = "ListenPlaybackInfoUseCase"
    }

    operator fun invoke(): Flow<PlaybackInfo?> {
        return callbackFlow {
            // Create listener
            val listener = object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    updatePlaybackInfo()
                }
                override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
                    updatePlaybackInfo()
                }
                fun updatePlaybackInfo() {
                    val mediaController = musicControllerFacade.mediaController.value!!
                    val playbackInfo = PlaybackInfo(
                        title = mediaController.mediaMetadata.title?.toString() ?: "",
                        artistName = mediaController.mediaMetadata.artist?.toString() ?: "",
                        albumArtUri = mediaController.mediaMetadata.artworkUri,
                        isPlaying = mediaController.isPlaying
                    )
                    trySendBlocking(playbackInfo)
                        .onSuccess { Log.d(TAG, "Sent $playbackInfo") }
                        .onFailure { Log.e(TAG, "Cannot send $playbackInfo", it) }
                }
            }
            // Add listener when ready
            musicControllerFacade.mediaController
                .collect { maybeMediaController -> maybeMediaController?.addListener(listener) }
            // Remove listener only if MediaController is present
            awaitClose { musicControllerFacade.mediaController.value?.removeListener(listener) }
        }
    }

}