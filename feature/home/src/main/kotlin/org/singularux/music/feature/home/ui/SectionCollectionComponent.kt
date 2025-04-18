package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.LibraryMusic
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.home.R

data class SectionCollectionComponentData(
    val numberOfArtists: Int,
    val numberOfAlbums: Int,
    val numberOfPlaylists: Int
)

enum class SectionCollectionComponentAction {
    ARTISTS, ALBUMS, PLAYLISTS, RECENT
}

@Composable
fun SectionCollectionComponent(
    modifier: Modifier = Modifier,
    data: SectionCollectionComponentData,
    onClick: (SectionCollectionComponentAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.section_collection_header),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionCollectionComponentCard(
                title = stringResource(R.string.section_collection_artists),
                subtitle = if (data.numberOfArtists > 0) {
                    stringResource(R.string.section_collection_found, data.numberOfArtists)
                } else {
                    stringResource(R.string.section_collection_empty)
                },
                icon = Icons.Rounded.Person,
                onClick = { onClick(SectionCollectionComponentAction.ARTISTS) }
            )
            SectionCollectionComponentCard(
                title = stringResource(R.string.section_collection_albums),
                subtitle = if (data.numberOfAlbums > 0) {
                    stringResource(R.string.section_collection_found, data.numberOfAlbums)
                } else {
                    stringResource(R.string.section_collection_empty)
                },
                icon = Icons.Rounded.Album,
                onClick = { onClick(SectionCollectionComponentAction.ALBUMS) }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionCollectionComponentCard(
                title = stringResource(R.string.section_collection_playlists),
                subtitle = if (data.numberOfPlaylists > 0) {
                    stringResource(R.string.section_collection_found, data.numberOfPlaylists)
                } else {
                    stringResource(R.string.section_collection_empty)
                },
                icon = Icons.Rounded.LibraryMusic,
                onClick = { onClick(SectionCollectionComponentAction.PLAYLISTS) }
            )
            SectionCollectionComponentCard(
                title = stringResource(R.string.section_collection_recent),
                subtitle = null,
                icon = Icons.Rounded.History,
                onClick = { onClick(SectionCollectionComponentAction.RECENT) }
            )
        }
    }
}

@Composable
fun RowScope.SectionCollectionComponentCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String?,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(104.dp)
            .weight(1F)
            .clip(shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .padding(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.End),
                imageVector = icon,
                contentDescription = title
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        SectionCollectionComponent(
            data = SectionCollectionComponentData(
                numberOfArtists = 10,
                numberOfAlbums = 12,
                numberOfPlaylists = 2
            ),
            onClick = {}
        )
    }
}