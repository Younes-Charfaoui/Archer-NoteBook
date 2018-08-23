package me.mxcsyounes.archernotebook.database.converters

import android.arch.persistence.room.TypeConverter
import java.util.*

object DateConverters {

    @TypeConverter
    fun toDate(time: Long?): Date? {
        return if (time == null) null else Date(time)
    }

    @TypeConverter
    fun toLong(date: Date?): Long? {
        return date?.time
    }
}
