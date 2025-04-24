package org.singularux.music.data.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class TrackFilterPreferences(
    val sortingOrder: TrackSortingOrder,
    val showTrashedItems: Boolean
)