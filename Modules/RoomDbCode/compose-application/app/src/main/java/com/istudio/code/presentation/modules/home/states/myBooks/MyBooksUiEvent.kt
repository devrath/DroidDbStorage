package com.istudio.code.presentation.modules.home.states.myBooks

import com.istudio.code.domain.database.models.Book

sealed class MyBooksUiEvent {
    object GetMyBooks : MyBooksUiEvent()
    data class DeleteBook(val book: Book) : MyBooksUiEvent()

    data class ConfirmDeleteBook(val book: Book) : MyBooksUiEvent()

}
