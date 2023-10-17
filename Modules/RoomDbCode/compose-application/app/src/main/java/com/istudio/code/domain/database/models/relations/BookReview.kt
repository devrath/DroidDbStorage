package com.istudio.code.domain.database.models.relations

import android.os.Parcelable
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Review
import kotlinx.parcelize.Parcelize
@Parcelize
data class BookReview(
    val review: Review,
    val book: Book
) : Parcelable