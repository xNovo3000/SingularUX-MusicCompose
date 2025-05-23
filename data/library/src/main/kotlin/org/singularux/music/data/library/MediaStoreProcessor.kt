package org.singularux.music.data.library

import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.singularux.music.data.library.subscriber.MediaStoreUpdateSubscriber

class MediaStoreProcessor(
    private val coroutineScope: CoroutineScope
) : ContentObserver(Handler(Looper.getMainLooper())) {

    private val subscribers = mutableListOf<MediaStoreUpdateSubscriber>()
    private var currentUpdateJob: Job? = null

    override fun onChange(selfChange: Boolean) {
        currentUpdateJob?.cancel()
        currentUpdateJob = coroutineScope.launch {
            for (subscriber in subscribers) {
                subscriber.onUpdate()
            }
        }
    }

    override fun onChange(selfChange: Boolean, uris: Collection<Uri?>, flags: Int) {
        onChange(selfChange)
    }

    fun subscribe(subscriber: MediaStoreUpdateSubscriber) {
        subscribers.add(subscriber)
    }

    fun unsubscribe(subscriber: MediaStoreUpdateSubscriber) {
        subscribers.remove(subscriber)
    }

}