package org.singularux.music.feature.playback.domain

import android.util.Log
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.singularux.music.feature.playback.foreground.MusicControllerFacade
import javax.inject.Inject

@ActivityRetainedScoped
class ListenPlaybackProgressUseCase @Inject constructor(
    private val musicControllerFacade: MusicControllerFacade
) {

    companion object {
        private const val TAG = "ListenPlaybackProgressUseCase"
    }

    operator fun invoke(): Flow<Float?> {
        return callbackFlow {
            val runningJob = launch {
                withContext(Dispatchers.Default) {
                    while (true) {
                        val result = withContext(Dispatchers.Main) {
                            musicControllerFacade.mediaController.value
                                ?.let { mediaController ->
                                    val current = mediaController.currentPosition.toDouble()
                                    val total = mediaController.contentDuration
                                    (current / total).toFloat()
                                }
                        }
                        trySendBlocking(result)
                            .onSuccess { Log.d(TAG, "Sent $result") }
                            .onFailure { Log.e(TAG, "Cannot send $result", it) }
                        delay(1000)
                    }
                }
            }
            awaitClose { runningJob.cancel() }
        }
    }

}