package com.istudio.code.domain.database.models.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Review
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewAndBook(
    @Embedded
    val review: Review,
    @Relation(parentColumn = "bookId", entityColumn = "id")
    val book: Book
) : Parcelable
