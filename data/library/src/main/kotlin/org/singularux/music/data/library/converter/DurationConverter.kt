package org.singularux.music.data.library.converter

import androidx.room.TypeConverter
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object DurationConverter {

    @TypeConverter
    fun fromDurationToLong(duration: Duration?): Long? {
        return duration?.inWholeMilliseconds
    }

    @TypeConverter
    fun fromLongToDuration(duration: Long?): Duration? {
        return duration?.milliseconds
    }

}