package org.singularux.music.feature.home.model

import org.singularux.music.feature.common.model.Album
import org.singularux.music.feature.common.model.Track

sealed class SearchResults {
    data object None : SearchResults()
    data class Some(
        val tracks: List<Track>,
        val albums: List<Album>,
        val hasMoreTracks: Boolean,
        val hasMoreAlbums: Boolean
    ) : SearchResults()
}