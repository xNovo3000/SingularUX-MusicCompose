package org.singularux.music.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.singularux.music.data.settings.model.PlaylistFilterPreferences
import org.singularux.music.data.settings.model.TrackFilterPreferences
import org.singularux.music.data.settings.serializer.PlaylistFilterPreferencesSerializer
import org.singularux.music.data.settings.serializer.TrackFilterPreferencesSerializer
import java.io.File

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSettingsModule {

    @Provides
    @ActivityRetainedScoped
    fun providesTrackFilterPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<TrackFilterPreferences> {
        return DataStoreFactory
            .create(
                serializer = TrackFilterPreferencesSerializer,
                produceFile = {
                    File("${context.filesDir}/track_filter_preferences.pref")
                },
                corruptionHandler = ReplaceFileCorruptionHandler {
                    TrackFilterPreferencesSerializer.defaultValue
                }
            )
    }

    @Provides
    @ActivityRetainedScoped
    fun providesPlaylistFilterPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<PlaylistFilterPreferences> {
        return DataStoreFactory
            .create(
                serializer = PlaylistFilterPreferencesSerializer,
                produceFile = {
                    File("${context.filesDir}/playlist_filter_preferences.pref")
                },
                corruptionHandler = ReplaceFileCorruptionHandler {
                    PlaylistFilterPreferencesSerializer.defaultValue
                }
            )
    }

}