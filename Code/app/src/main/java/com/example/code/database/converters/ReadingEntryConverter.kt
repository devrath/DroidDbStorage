package com.example.code.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.example.code.App
import com.example.code.model.ReadingEntry

class ReadingEntryConverter {

  @TypeConverter
  fun fromEntries(list: List<ReadingEntry>): String = App.gson.toJson(list)

  @TypeConverter
  fun toEntries(json: String): List<ReadingEntry> {
    val listType = object : TypeToken<List<ReadingEntry>>() {}.type

    return try {
      App.gson.fromJson(json, listType)
    } catch (error: Throwable) {
      emptyList()
    }
  }
}