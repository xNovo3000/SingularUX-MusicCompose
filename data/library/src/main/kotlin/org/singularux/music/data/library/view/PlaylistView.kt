package org.singularux.music.data.library.view

import androidx.room.DatabaseView
import kotlinx.datetime.Instant

@DatabaseView(
    "SELECT p.id, p.title, p.creationDate, COUNT(*) AS numberOfItems " +
        "FROM Playlist p " +
        "INNER JOIN PlayListItem i ON p.id = i.playlistId " +
        "GROUP BY p.id"
)
data class PlaylistView(
    val id: Int,
    val title: String,
    val creationDate: Instant,
    val numberOfItems: Int
)