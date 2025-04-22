package org.singularux.music.feature.home.model

import androidx.annotation.StringRes
import org.singularux.music.feature.common.model.Track
import org.singularux.music.feature.home.R

enum class TrackSortingStrategy(
    @StringRes val displayNameRes: Int,
    val sortingFunction: (List<Track>) -> List<Track>
) {
    AZ(
        displayNameRes = R.string.sorting_strategy_az,
        sortingFunction = { trackList -> trackList.sortedBy { it.title } }
    ),
    DECREASING_DURATION(
        displayNameRes = R.string.sorting_strategy_decreasing_duration,
        sortingFunction = { trackList -> trackList.sortedByDescending { it.duration } }
    ),
}