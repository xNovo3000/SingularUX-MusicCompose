package org.singularux.music

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.singularux.music.core.ui.MusicSurface
import org.singularux.music.core.ui.MusicTheme

@Serializable
sealed class MusicRoute {
    @Serializable data object Home : MusicRoute()
}

@ExperimentalMaterial3Api
@Composable
fun MusicUI(modifier: Modifier = Modifier) {
    MusicTheme {
        MusicSurface(modifier = modifier) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = MusicRoute.Home
            ) {

            }
        }
    }
}