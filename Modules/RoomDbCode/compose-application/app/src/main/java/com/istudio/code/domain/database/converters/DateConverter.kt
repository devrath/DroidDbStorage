package com.istudio.code.domain.database.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    /**
     * This we will take a long(Primitive value) value from DB and convert into a Date(Complex Object) object to display
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return Date(value ?: 0)
    }

    /**
     * This we will use date(complex type) and store as a Long(Primitive type) value to be stored in DB
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long = date?.time ?: 0

}