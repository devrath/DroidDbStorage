package com.example.code.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.code.model.Book
import com.example.code.model.Genre

class BooksByGenre(
    @Embedded
    val genre: Genre,
    @Relation(parentColumn = "id", entityColumn = "bookGenreId")
    val books: List<Book>?
)