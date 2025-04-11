package org.singularux.music.feature.home.ui

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.music.core.ui.MusicSurface
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.home.R

@ExperimentalMaterial3Api
@Composable
fun SearchBarInputField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    SearchBarDefaults.InputField(
        modifier = modifier,
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        leadingIcon = {

        },
        trailingIcon = {
            if (expanded) {
                IconButton(
                    onClick = { onQueryChange("") }  // Clear "query"
                ) {

                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun SearchBarComponent(modifier: Modifier = Modifier) {

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    MusicTheme {
        MusicSurface {

        }
    }
}