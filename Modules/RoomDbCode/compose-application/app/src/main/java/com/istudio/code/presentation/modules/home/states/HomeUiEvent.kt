package com.istudio.code.presentation.modules.home.states

import com.istudio.code.domain.database.models.Book

sealed class HomeUiEvent {
    object GetMyBooks : HomeUiEvent()
    data class DeleteBook(val book: Book) : HomeUiEvent()
}
