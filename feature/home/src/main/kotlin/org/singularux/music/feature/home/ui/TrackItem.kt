package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.PlaylistAdd
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.home.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class TrackItemData(
    val id: Int,
    val title: String,
    val artistName: String,
    val artistId: Int,
    val albumName: String,
    val albumId: Int,
    val albumArtUri: String?,
    val duration: Duration
)

enum class TrackItemAction {
    LISTEN, ADD_TO_QUEUE, SHOW_ARTIST, SHOW_ALBUM
}

@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    data: TrackItemData,
    onAction: (TrackItemAction) -> Unit
) {
    val state = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        confirmValueChange = {
            onAction(TrackItemAction.ADD_TO_QUEUE)
            false
        },
        positionalThreshold = SwipeToDismissBoxDefaults.positionalThreshold
    )
    SwipeToDismissBox(
        modifier = modifier,
        state = state,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp),
                    imageVector = Icons.AutoMirrored.Rounded.PlaylistAdd,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentDescription = stringResource(R.string.track_item_add_to_queue)
                )
            }
        },
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart = false,
    ) {
        TrackItemInner(
            data = data,
            onAction = onAction
        )
    }
}

@Composable
private fun TrackItemInner(
    modifier: Modifier = Modifier,
    data: TrackItemData,
    onAction: (TrackItemAction) -> Unit
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = { onAction(TrackItemAction.LISTEN) }),
        headlineContent = {
            Text(text = data.title)
        },
        supportingContent = {
            Text(
                text = stringResource(
                    R.string.track_item_supporting,
                    data.duration.inWholeMinutes,
                    data.duration.inWholeSeconds % 60,
                    data.artistName
                )
            )
        },
        leadingContent = {
            AsyncImage(
                modifier = Modifier.size(56.dp),
                model = data.albumArtUri,
                placeholder = rememberVectorPainter(Icons.Rounded.MusicNote),  // TODO: Better placeholder
                contentDescription = data.albumName
            )
        },
        trailingContent = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = stringResource(R.string.track_item_more_vert)
                )
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        TrackItem(
            data = TrackItemData(
                id = 1,
                title = "Test",
                artistName = "Test artist",
                artistId = 1,
                albumName = "Test album",
                albumId = 2,
                albumArtUri = null,
                duration = 312.seconds
            ),
            onAction = {}
        )
    }
}