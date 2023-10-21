package com.istudio.code.presentation.modules.home.states.myBooks

import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.relations.BookAndGenre

data class MyBooksUiState(
    val books: List<BookAndGenre> = emptyList(),
    var book : Book? = null,
    val launchedEffectState: Boolean = false,
)
