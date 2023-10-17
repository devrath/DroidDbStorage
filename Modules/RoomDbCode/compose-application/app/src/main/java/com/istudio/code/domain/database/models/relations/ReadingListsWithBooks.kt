package com.istudio.code.domain.database.models.relations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ReadingListsWithBooks(
    val id: String,
    val name: String,
    val books: List<BookAndGenre>
) : Parcelable