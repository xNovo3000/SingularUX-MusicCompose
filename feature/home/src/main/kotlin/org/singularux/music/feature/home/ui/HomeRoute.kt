package org.singularux.music.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.lifecycle.viewmodel.compose.viewModel
import org.singularux.music.feature.common.model.Track
import org.singularux.music.feature.common.ui.TrackItem
import org.singularux.music.feature.home.viewmodel.HomeViewModel

@ExperimentalMaterial3Api
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        // Main content
        val trackList by viewModel.trackList.collectAsState()
        LazyColumn {
            item(key = "collection") {

            }
            item(key = "tracks_header") {

            }
            items(
                items = trackList,
                key = { it.key },
                contentType = { it }
            ) {
                TrackItem(
                    track = it,
                    onAction = {}
                )
            }
        }
        // Search bar
        var query by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        SearchBarComponent(
            query = query,
            onQueryChange = { query = it },
            onSearch = {},
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            if (expanded) {
                if (query.isEmpty()) {
                    val searchSuggestions by viewModel.searchSuggestions.collectAsState()
                    SearchBarSuggestionComponent(
                        suggestions = searchSuggestions,
                        onClick = { query = it }
                    )
                } else {

                }
            }
        }
    }
}

private val Track.key: String
    get() = "Track${id}"