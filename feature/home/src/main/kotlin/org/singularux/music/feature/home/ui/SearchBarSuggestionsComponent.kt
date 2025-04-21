package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.music.core.ui.MusicTheme

@Composable
fun SearchBarSuggestionComponent(
    modifier: Modifier = Modifier,
    suggestions: List<String>,
    onClick: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(state = scrollState)
    ) {
        suggestions.forEach {
            SuggestionItem(
                suggestion = it,
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
private fun SuggestionItem(
    modifier: Modifier = Modifier,
    suggestion: String,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onClick),
        headlineContent = {
            Text(
                text = suggestion,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingContent = {
            Icon(
                imageVector = Icons.Rounded.History,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = suggestion
            )
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {
        SearchBarSuggestionComponent(
            suggestions = List(10) { "Suggestion $it" },
            onClick = {}
        )
    }
}