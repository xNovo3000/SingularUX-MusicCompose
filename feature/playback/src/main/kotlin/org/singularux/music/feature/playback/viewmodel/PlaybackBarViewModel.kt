package org.singularux.music.feature.playback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.singularux.music.feature.playback.domain.ListenPlaybackInfoUseCase
import org.singularux.music.feature.playback.domain.ListenPlaybackProgressUseCase
import org.singularux.music.feature.playback.foreground.MusicControllerFacade
import javax.inject.Inject

@HiltViewModel
class PlaybackBarViewModel @Inject constructor(
    listenPlaybackInfoUseCase: ListenPlaybackInfoUseCase,
    listenPlaybackProgressUseCase: ListenPlaybackProgressUseCase
) : ViewModel() {
    val playbackInfo = listenPlaybackInfoUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    val playbackProgress = listenPlaybackProgressUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}