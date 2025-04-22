package org.singularux.music.feature.common.ui

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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.common.R
import org.singularux.music.feature.common.model.Track
import kotlin.time.Duration.Companion.seconds

enum class TrackItemAction {
    LISTEN, ADD_TO_QUEUE, SHOW_ARTIST, SHOW_ALBUM
}

@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    track: Track,
    onAction: (TrackItemAction) -> Unit
) {
    val state = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        confirmValueChange = {
            onAction(TrackItemAction.ADD_TO_QUEUE)
            false
        }
    )
    SwipeToDismissBox(
        modifier = modifier,
        state = state,
        backgroundContent = {
            TrackItemBackgroundContent(modifier = Modifier.fillMaxSize())
        },
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart = false,
    ) {
        TrackItemStateless(
            track = track,
            onAction = onAction
        )
    }
}

@Composable
private fun TrackItemBackgroundContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
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
}

@Composable
fun TrackItemStateless(
    modifier: Modifier = Modifier,
    track: Track,
    onAction: (TrackItemAction) -> Unit
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = { onAction(TrackItemAction.LISTEN) }),
        headlineContent = {
            Text(text = track.title)
        },
        supportingContent = {
            Text(
                text = stringResource(
                    R.string.track_item_supporting,
                    track.duration.inWholeMinutes,
                    track.duration.inWholeSeconds % 60,
                    track.artistName
                )
            )
        },
        leadingContent = {
            val placeholderPainter = rememberVectorPainter(Icons.Rounded.MusicNote)  // TODO: Better placeholder
            AsyncImage(
                modifier = Modifier.size(56.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(track.albumArtUri)
                    .crossfade(durationMillis = 300)
                    .size(size = with(LocalDensity.current) { 56.dp.toPx().toInt() })
                    .build(),
                placeholder = placeholderPainter,
                error = placeholderPainter,
                contentDescription = track.albumName
            )
        },
        trailingContent = {
            TrackItemStatelessTrailing(onAction = onAction)
        }
    )
}

@Composable
fun TrackItemStatelessTrailing(
    modifier: Modifier = Modifier,
    onAction: (TrackItemAction) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = stringResource(R.string.track_item_more_vert)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(R.string.track_item_add_to_queue))
                },
                onClick = {
                    onAction(TrackItemAction.ADD_TO_QUEUE)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(R.string.track_item_show_artist))
                },
                onClick = {
                    onAction(TrackItemAction.SHOW_ARTIST)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(R.string.track_item_show_album))
                },
                onClick = {
                    onAction(TrackItemAction.SHOW_ALBUM)
                    expanded = false
                }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        TrackItem(
            track = Track(
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