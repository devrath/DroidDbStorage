package com.istudio.code.domain.database.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre

class BooksByGenre(
    @Embedded
    val genre: Genre,
    @Relation(parentColumn = "id", entityColumn = "bookGenreId")
    val books: List<Book>?
)