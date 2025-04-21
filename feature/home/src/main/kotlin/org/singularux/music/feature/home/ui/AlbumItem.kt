package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import org.singularux.music.feature.home.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class AlbumItemData(
    val id: Int,
    val name: String,
    val albumArtUri: String?,
    val numberOfTracks: Int,
    val duration: Duration
)

@Composable
fun AlbumItem(
    modifier: Modifier = Modifier,
    data: AlbumItemData,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onClick),
        headlineContent = { Text(text = data.name) },
        supportingContent = {
            Text(
                text = stringResource(
                    R.string.album_item_support,
                    data.duration.inWholeMinutes,
                    data.duration.inWholeSeconds % 60,
                    data.numberOfTracks
                )
            )
        },
        leadingContent = {
            val albumArtPlaceholder = rememberVectorPainter(Icons.Rounded.MusicNote)  // TODO: Better placeholder
            AsyncImage(
                modifier = Modifier.size(56.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.albumArtUri)
                    .crossfade(durationMillis = 300)
                    .size(size = with(LocalDensity.current) { 56.dp.toPx().toInt() })
                    .build(),
                placeholder = albumArtPlaceholder,
                error = albumArtPlaceholder,
                contentDescription = data.name
            )
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        AlbumItem(
            data = AlbumItemData(
                id = 1,
                name = "My Album",
                albumArtUri = null,
                numberOfTracks = 10,
                duration = 697.seconds
            ),
            onClick = {}
        )
    }
}