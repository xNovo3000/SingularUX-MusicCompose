package org.singularux.music.data.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class PlaylistFilterPreferences(
    val sortingOrder: PlaylistSortingOrder
)