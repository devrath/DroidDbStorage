package com.istudio.code.domain.database.models.relations

import android.os.Parcelable
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import kotlinx.parcelize.Parcelize
@Parcelize
data class BookAndGenre(
    val book: Book,
    val genre: Genre
) : Parcelable