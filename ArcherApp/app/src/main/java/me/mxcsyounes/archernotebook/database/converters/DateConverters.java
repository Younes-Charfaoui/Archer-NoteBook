package me.mxcsyounes.archernotebook.database.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverters {

    @TypeConverter
    public static Date toDate(Long time) {
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static Long toLong(Date date) {
        return date == null ? null : date.getTime();
    }
}
