package org.singularux.music.feature.playback

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.singularux.music.feature.playback.foreground.MusicControllerFacade

@Module
@InstallIn(SingletonComponent::class)
object FeaturePlaybackModule {

    @Provides
    @ActivityRetainedScoped
    fun providesMusicControllerFacade(@ApplicationContext context: Context): MusicControllerFacade {
        return MusicControllerFacade(
            context = context,
            coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        )
    }

}