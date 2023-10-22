package com.istudio.code.domain.database.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Review(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "bookId")
    val bookId: String,
    val rating: Int,
    val notes: String,
    // val entries: List<ReadingEntry>,
    // val lastUpdatedDate: Date
) : Parcelable