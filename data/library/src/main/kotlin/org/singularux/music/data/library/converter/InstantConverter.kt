package org.singularux.music.data.library.converter

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

object InstantConverter {

    @TypeConverter
    fun fromInstantToLong(instant: Instant?): Long? {
        return instant?.epochSeconds
    }

    @TypeConverter
    fun fromLongToInstant(instant: Long?): Instant? {
        return instant?.let { Instant.fromEpochSeconds(it) }
    }

}