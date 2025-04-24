package org.singularux.music.feature.playback.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.singularux.music.feature.playback.foreground.MusicControllerFacade
import javax.inject.Inject

@HiltViewModel
class PlaybackBarViewModel @Inject constructor(
    musicControllerFacade: MusicControllerFacade
) : ViewModel() {

    val mediaController = musicControllerFacade.mediaController

}