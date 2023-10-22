package com.istudio.code.domain.database.models.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import kotlinx.parcelize.Parcelize
@Parcelize
data class BookAndGenre(
    @Embedded
    val book: Book,
    @Relation(parentColumn = "bookGenreId", entityColumn = "id")
    val genre: Genre
) : Parcelable