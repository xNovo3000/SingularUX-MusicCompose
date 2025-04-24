package org.singularux.music.feature.playback.foreground

import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class MusicControllerFacade @Inject constructor(
    @ApplicationContext context: Context,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
) {

    companion object {
        private const val TAG = "MusicControllerFacade"
    }

    private val mutableMediaController = MutableStateFlow<MediaController?>(null)
    val mediaController: StateFlow<MediaController?>
        get() = mutableMediaController

    init {
        coroutineScope.launch {
            val componentName = ComponentName(context, MusicPlaybackService::class.java)
            val sessionToken = SessionToken(context, componentName)
            val mediaController = try {
                MediaController.Builder(context, sessionToken)
                    .buildAsync()
                    .await()
            } catch (e: Exception) {
                Log.e(TAG, "Cannot initialize MediaController instance", e)
                null
            }
            mutableMediaController.update { mediaController }
        }
    }

}