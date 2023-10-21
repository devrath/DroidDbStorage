package com.istudio.code.domain.database.models.relations

import android.os.Parcelable
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Review
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewAndBook(
    val book: Book,
    val review: Review
) : Parcelable
