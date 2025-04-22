package org.singularux.music.feature.home.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListenSearchSuggestionsUseCase @Inject constructor() {

    companion object {
        private const val TAG = "GetSearchSuggestionsUseCase"
    }

    operator fun invoke(): Flow<List<String>> {
        // TODO: Implement
        return flow {
            List(5) { "Suggestion $it" }
        }
    }

}