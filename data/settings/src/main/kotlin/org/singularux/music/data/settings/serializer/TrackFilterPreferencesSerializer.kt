package org.singularux.music.data.settings.serializer

import android.util.Log
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.singularux.music.data.settings.model.TrackFilterPreferences
import org.singularux.music.data.settings.model.TrackSortingOrder
import java.io.InputStream
import java.io.OutputStream

object TrackFilterPreferencesSerializer : Serializer<TrackFilterPreferences> {

    private const val TAG = "TrackFilterPreferencesSerializer"

    override val defaultValue: TrackFilterPreferences
        get() = TrackFilterPreferences(
            sortingOrder = TrackSortingOrder.AZ,
            showTrashedItems = false
        )

    override suspend fun readFrom(input: InputStream): TrackFilterPreferences {
        return try {
            Json.decodeFromString(
                deserializer = TrackFilterPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            Log.e(TAG, "readFrom(): cannot deserialize from byte stream", e)
            defaultValue
        } catch (e: Exception) {
            Log.e(TAG, "readFrom(): unknown error", e)
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: TrackFilterPreferences,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = TrackFilterPreferences.serializer(),
                value = t
            ).toByteArray()
        )
    }

}