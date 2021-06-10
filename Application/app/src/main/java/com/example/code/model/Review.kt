package com.example.code.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.code.database.converters.DateConverter
import com.example.code.database.converters.ReadingEntryConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Review(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val bookId: String,
    val rating: Int,
    val notes: String,
    val imageUrl: String,
    @TypeConverters(DateConverter::class)
    val lastUpdatedDate: Date,
    @TypeConverters(ReadingEntryConverter::class)
    val entries: List<ReadingEntry>
) : Parcelable