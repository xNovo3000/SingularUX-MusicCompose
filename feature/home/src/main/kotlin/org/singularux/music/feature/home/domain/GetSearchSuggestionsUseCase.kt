package org.singularux.music.feature.home.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchSuggestionsUseCase @Inject constructor() {

    companion object {
        private const val TAG = "GetSearchSuggestionsUseCase"
    }

    // TODO: Implement
    operator fun invoke(): Flow<List<String>> {
        return flow {
            emit(value = List(6) { "Suggestion $it" })
        }
    }

}