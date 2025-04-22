package org.singularux.music.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.singularux.music.feature.home.domain.ListenSearchSuggestionsUseCase
import org.singularux.music.feature.home.domain.ListenTrackListUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    listenTrackListUseCase: ListenTrackListUseCase,
    listenSearchSuggestionsUseCase: ListenSearchSuggestionsUseCase
) : ViewModel() {

    val trackList = listenTrackListUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val searchSuggestions = listenSearchSuggestionsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

}