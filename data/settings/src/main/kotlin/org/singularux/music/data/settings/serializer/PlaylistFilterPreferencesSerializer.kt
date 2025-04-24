package org.singularux.music.data.settings.serializer

import android.util.Log
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.singularux.music.data.settings.model.PlaylistFilterPreferences
import org.singularux.music.data.settings.model.PlaylistSortingOrder
import java.io.InputStream
import java.io.OutputStream

object PlaylistFilterPreferencesSerializer : Serializer<PlaylistFilterPreferences> {

    private const val TAG = "PlaylistFilterPreferencesSerializer"

    override val defaultValue: PlaylistFilterPreferences
        get() = PlaylistFilterPreferences(
            sortingOrder = PlaylistSortingOrder.AZ
        )

    override suspend fun readFrom(input: InputStream): PlaylistFilterPreferences {
        return try {
            Json.decodeFromString(
                deserializer = PlaylistFilterPreferences.serializer(),
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
        t: PlaylistFilterPreferences,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = PlaylistFilterPreferences.serializer(),
                value = t
            ).toByteArray()
        )
    }

}