package org.singularux.music.data.library

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.singularux.music.data.library.observer.MediaStoreProcessor
import org.singularux.music.data.library.repository.PlaylistRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataLibraryModule {

    @Provides
    @ActivityRetainedScoped
    fun providesMediaStoreIntegrationDatabase(
        @ApplicationContext context: Context
    ): MediaStoreIntegrationDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = MediaStoreIntegrationDatabase::class.java,
                name = "media-store-integration"
            )
            .build()
    }

    @Provides
    @ActivityRetainedScoped
    fun providesPlaylistRepository(
        mediaStoreIntegrationDatabase: MediaStoreIntegrationDatabase
    ): PlaylistRepository {
        return mediaStoreIntegrationDatabase.getPlaylistRepository()
    }

    @Provides
    @ActivityRetainedScoped
    fun providesMediaStoreProcessor(): MediaStoreProcessor {
        return MediaStoreProcessor(
            coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        )
    }

}