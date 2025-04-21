package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.music.core.ui.MusicTheme

data class SearchBarResultsComponentData(
    val tracks: List<TrackItemData>,
    val hasMoreTracks: Boolean
    // TODO: Add albums, artists, playlists, etc...
)

sealed class SearchBarResultsComponentAction {
    data class Track(val data: TrackItemData) : SearchBarResultsComponentAction()
}

@Composable
fun SearchBarResultsComponent(
    modifier: Modifier = Modifier,
    data: SearchBarResultsComponentData,
    onAction: (SearchBarResultsComponentAction) -> Unit,
    onScroll: () -> Unit
) {
    LazyColumn(modifier = modifier) {

    }
}

@Composable
fun SearchBarResultsHeaderComponent(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {

    }
}