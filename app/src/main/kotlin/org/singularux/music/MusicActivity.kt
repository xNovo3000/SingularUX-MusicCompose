package org.singularux.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.singularux.music.core.permission.MusicPermission
import org.singularux.music.core.permission.MusicPermissionManager
import org.singularux.music.data.library.MediaStoreProcessor
import org.singularux.music.data.library.repository.AlbumRepositoryAndroid
import org.singularux.music.data.library.repository.ArtistRepositoryAndroid
import org.singularux.music.data.library.repository.TrackRepositoryAndroid
import javax.inject.Inject

@AndroidEntryPoint
class MusicActivity : ComponentActivity() {

    @Inject lateinit var mediaStoreProcessor: MediaStoreProcessor
    @Inject lateinit var musicPermissionManager: MusicPermissionManager

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent { MusicUI() }
    }

    override fun onResume() {
        super.onResume()
        // Register Music ContentObserver
        if (musicPermissionManager.hasPermission(MusicPermission.READ_MUSIC)) {
            contentResolver.registerContentObserver(
                AlbumRepositoryAndroid.URI, true, mediaStoreProcessor)
            contentResolver.registerContentObserver(
                ArtistRepositoryAndroid.URI, true, mediaStoreProcessor)
            contentResolver.registerContentObserver(
                TrackRepositoryAndroid.URI, true, mediaStoreProcessor)
        }
    }

    override fun onPause() {
        super.onPause()
        // Unregister Music ContentObserver
        if (musicPermissionManager.hasPermission(MusicPermission.READ_MUSIC)) {
            contentResolver.unregisterContentObserver(mediaStoreProcessor)
        }
    }

}