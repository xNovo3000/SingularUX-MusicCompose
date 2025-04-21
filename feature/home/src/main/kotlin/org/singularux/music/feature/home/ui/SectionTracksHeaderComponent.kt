package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.home.R

enum class TrackSortingStrategy(@StringRes val displayNameRes: Int) {
    AZ(R.string.sorting_strategy_az),
    DECREASING_DURATION(R.string.sorting_strategy_decreasing_duration)
}

data class SectionTracksHeaderComponentData(
    val sortingStrategy: TrackSortingStrategy
)

sealed class SectionTracksHeaderComponentAction {
    data object SortingDialog : SectionTracksHeaderComponentAction()
}

@Composable
fun SectionTracksHeaderComponent(
    modifier: Modifier = Modifier,
    data: SectionTracksHeaderComponentData,
    onClick: (SectionTracksHeaderComponentAction) -> Unit
) {
    val sortingStrategyString = stringResource(data.sortingStrategy.displayNameRes)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.section_tracks_header),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1F))
        AssistChip(
            onClick = { onClick(SectionTracksHeaderComponentAction.SortingDialog) },
            label = {
                Text(
                    text = stringResource(
                        R.string.section_tracks_header_sorting,
                        sortingStrategyString
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.FilterList,
                    contentDescription = stringResource(R.string.section_tracks_header_sorting_desc)
                )
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        SectionTracksHeaderComponent(
            data = SectionTracksHeaderComponentData(
                sortingStrategy = TrackSortingStrategy.AZ
            ),
            onClick = {}
        )
    }
}