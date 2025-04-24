package org.singularux.music.data.library.repository

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.singularux.music.data.library.view.PlaylistView

@Dao
interface PlaylistRepository {
    @Query("SELECT * FROM PlaylistView") fun listenPlaylists(): Flow<PlaylistView>
}