package com.example.code.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.example.code.model.Book
import com.example.code.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookAndGenre(
    @Embedded
    val book: Book,
    @Relation(parentColumn = "bookGenreId", entityColumn = "id")
    val genre: Genre
) : Parcelable