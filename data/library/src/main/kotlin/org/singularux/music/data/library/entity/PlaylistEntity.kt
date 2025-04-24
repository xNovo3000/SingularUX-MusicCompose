package org.singularux.music.data.library.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "Playlist")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val creationDate: Instant,
    val cachedArtworkUri: String?
)