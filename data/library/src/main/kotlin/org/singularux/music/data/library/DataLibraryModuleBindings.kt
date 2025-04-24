package org.singularux.music.data.library

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.singularux.music.data.library.repository.AlbumRepository
import org.singularux.music.data.library.repository.AlbumRepositoryAndroid
import org.singularux.music.data.library.repository.ArtistRepository
import org.singularux.music.data.library.repository.ArtistRepositoryAndroid
import org.singularux.music.data.library.repository.TrackRepository
import org.singularux.music.data.library.repository.TrackRepositoryAndroid

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataLibraryModuleBindings {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindsTrackRepository(repository: TrackRepositoryAndroid): TrackRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindsAlbumRepository(repository: AlbumRepositoryAndroid): AlbumRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindsArtistRepository(repository: ArtistRepositoryAndroid): ArtistRepository

}