package org.singularux.music.feature.home.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.singularux.music.feature.common.model.Track
import javax.inject.Inject

class ListenTrackListUseCase @Inject constructor() {

    operator fun invoke(): Flow<List<Track>> {
        return flow {}
    }

}