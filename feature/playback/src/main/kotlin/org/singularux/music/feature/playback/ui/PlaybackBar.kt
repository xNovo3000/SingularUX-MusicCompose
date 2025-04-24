package org.singularux.music.feature.playback.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.singularux.music.core.ui.MusicTheme
import org.singularux.music.feature.playback.viewmodel.PlaybackBarViewModel

@Composable
fun PlaybackBar(
    modifier: Modifier = Modifier,
    viewModel: PlaybackBarViewModel = viewModel()
) {

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MusicTheme {

    }
}