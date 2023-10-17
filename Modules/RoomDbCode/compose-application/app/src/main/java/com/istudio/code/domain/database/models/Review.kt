package com.istudio.code.domain.database.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Review(
    val id: String = UUID.randomUUID().toString(),
    val bookId: String,
    val rating: Int,
    val notes: String,
    val imageUrl: String,
    val entries: List<ReadingEntry>,
    val lastUpdatedDate: Date
) : Parcelable