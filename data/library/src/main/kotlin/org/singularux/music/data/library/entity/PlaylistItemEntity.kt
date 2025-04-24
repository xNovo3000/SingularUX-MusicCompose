package org.singularux.music.data.library.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.time.Duration

@Entity(
    tableName = "PlayListItem",
    indices = [
        Index(
            value = ["playlistId"]
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["id"],
            childColumns = ["playlistId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlaylistItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val playlistId: Int,
    val uri: String,
    val cachedTitle: String,
    val cachedAlbumId: String?,
    val cachedArtworkUri: String?,
    val cachedArtistId: Int?,
    val cachedArtistName: String?,
    val cachedDuration: Duration
)