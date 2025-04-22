package org.singularux.music.feature.home.domain

import org.singularux.music.feature.home.model.SearchResults
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor() {

    suspend operator fun invoke(query: String): SearchResults {
        // TODO: Implement
        return SearchResults.None
    }

}