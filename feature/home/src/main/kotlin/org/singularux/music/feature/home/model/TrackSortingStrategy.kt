package org.singularux.music.feature.home.model

import androidx.annotation.StringRes
import org.singularux.music.feature.home.R

enum class TrackSortingStrategy(@StringRes val displayNameRes: Int) {
    AZ(R.string.sorting_strategy_az),
    DECREASING_DURATION(R.string.sorting_strategy_decreasing_duration)
}