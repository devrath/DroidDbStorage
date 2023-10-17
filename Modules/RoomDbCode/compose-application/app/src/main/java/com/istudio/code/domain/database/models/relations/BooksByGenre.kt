package com.istudio.code.domain.database.models.relations

import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre

class BooksByGenre(
    val genre: Genre,
    val books: List<Book>
)