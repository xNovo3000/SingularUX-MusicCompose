package org.singularux.music.data.library

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.singularux.music.data.library.converter.DurationConverter
import org.singularux.music.data.library.converter.InstantConverter
import org.singularux.music.data.library.entity.PlaylistEntity
import org.singularux.music.data.library.entity.PlaylistItemEntity
import org.singularux.music.data.library.repository.PlaylistRepository
import org.singularux.music.data.library.view.PlaylistView

@Database(
    entities = [PlaylistEntity::class, PlaylistItemEntity::class],
    views = [PlaylistView::class],
    version = 1
)
@TypeConverters(value = [InstantConverter::class, DurationConverter::class])
abstract class MediaStoreIntegrationDatabase : RoomDatabase() {
    abstract fun getPlaylistRepository(): PlaylistRepository
}