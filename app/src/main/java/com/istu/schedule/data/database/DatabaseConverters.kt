package com.istu.schedule.data.database

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DatabaseConverters {

    @TypeConverter
    fun dateTimeToIsoString(dateTime: LocalDateTime): String {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun datetimeFromIsoString(value: String): LocalDateTime {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME)
    }
}