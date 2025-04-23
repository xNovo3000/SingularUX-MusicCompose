package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.home.R
import org.singularux.music.feature.common.model.Album
import org.singularux.music.feature.home.model.SearchResults
import org.singularux.music.feature.common.model.Track
import org.singularux.music.feature.common.ui.AlbumItem
import org.singularux.music.feature.common.ui.TrackItem
import org.singularux.music.feature.common.ui.TrackItemAction
import kotlin.time.Duration.Companion.seconds

sealed class SearchBarResultsComponentAction {

}

@Composable
fun SearchBarResultsComponent(
    modifier: Modifier = Modifier,
    data: SearchResults.Some,
    onAction: (SearchBarResultsComponentAction) -> Unit,
    onScroll: () -> Unit
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
        state = scrollState
    ) {
        // TODO: Wrong colors, should be updated inside the ListItem. Or create a custom impl
        // Tracks
        item(key = "tracks") {
            SearchBarResultsHeaderComponent(
                modifier = Modifier
                    .padding(16.dp),
                text = stringResource(R.string.search_view_header_tracks),
                showSeeMoreButton = data.hasMoreTracks,
                onSeeMoreButtonClick = {
                    // onAction(SearchBarResultsComponentAction.AllTracks)
                }
            )
        }
        items(
            items = data.tracks,
            key = { it.key },
            contentType = { it }
        ) { trackItemData ->
            TrackItem(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
                track = trackItemData,
                onAction = {
                    // onAction(SearchBarResultsComponentAction.Track(trackItemData, it))
                }
            )
        }
        // Albums
        item(key = "albums") {
            SearchBarResultsHeaderComponent(
                modifier = Modifier
                    .padding(16.dp),
                text = stringResource(R.string.search_view_header_albums),
                showSeeMoreButton = data.hasMoreAlbums,
                onSeeMoreButtonClick = {
                    // onAction(SearchBarResultsComponentAction.AllAlbums)
                }
            )
        }
        items(
            items = data.albums,
            key = { it.key },
            contentType = { it }
        ) { album ->
            AlbumItem(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
                album = album,
                onClick = {
                    // onAction(SearchBarResultsComponentAction.Album(Album))
                }
            )
        }
        // TODO: Artists, playlists, etc...
    }
    // Listen for scroll
    val isScrolling by remember { derivedStateOf { scrollState.isScrollInProgress } }
    LaunchedEffect(isScrolling) {
        if (isScrolling) onScroll()
    }
}

@Composable
fun SearchBarResultsHeaderComponent(
    modifier: Modifier = Modifier,
    text: String,
    showSeeMoreButton: Boolean,
    onSeeMoreButtonClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.weight(1F))
        TextButton(
            onClick = onSeeMoreButtonClick,
            enabled = showSeeMoreButton
        ) {
            Text(text = stringResource(R.string.search_view_header_see_all))
        }
    }
}

private val Track.key: String
    get() = "Track${id}"

private val Album.key: String
    get() = "Album${id}"

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        SearchBarResultsComponent(
            data = SearchResults.Some(
                tracks = List(3) {
                    Track(
                        id = it,
                        title = "Test",
                        artistName = "Test artist",
                        artistId = 1,
                        albumName = "Test album",
                        albumId = 2,
                        albumArtUri = null,
                        duration = 312.seconds
                    )
                },
                albums = List(3) {
                    Album(
                        id = it,
                        title = "My Album",
                        albumArtUri = null,
                        numberOfTracks = 10
                    )
                },
                hasMoreTracks = false,
                hasMoreAlbums = true
            ),
            onAction = {},
            onScroll = {}
        )
    }
}