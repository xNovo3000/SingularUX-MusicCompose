package org.singularux.music.data.library.subscriber

interface MediaStoreUpdateSubscriber {
    suspend fun onUpdate()
}