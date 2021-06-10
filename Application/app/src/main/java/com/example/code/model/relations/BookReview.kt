package com.example.code.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.example.code.model.Book
import com.example.code.model.Review
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookReview(
    @Embedded
    val review: Review,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "id"
    )
    val book: Book
) : Parcelable