package com.istudio.code.domain.database.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.istudio.code.domain.database.converters.DateConverter
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Book::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("bookId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Review(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "bookId")
    val bookId: String,
    val rating: Int,
    val notes: String,
    // val entries: List<ReadingEntry>,
    @TypeConverters(DateConverter::class)
    val lastUpdatedDate: Date
) : Parcelable