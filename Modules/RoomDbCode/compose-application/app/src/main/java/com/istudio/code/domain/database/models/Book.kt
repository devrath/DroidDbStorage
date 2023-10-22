package com.istudio.code.domain.database.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "books",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Genre::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("bookGenreId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
class Book(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    @ColumnInfo(name = "bookGenreId")
    val genreId: String
) : Parcelable