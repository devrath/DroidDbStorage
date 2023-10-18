package com.istudio.code.presentation.modules.home.states

import com.istudio.code.domain.database.models.Book

sealed class HomeUiEvent {
    //object HomeScreenSuccess : HomeScreenResponseEvent()

    object GetMyBooks : HomeUiEvent()
    //data class MyBooks(val books: List<Book>) : HomeUiEvent()
}
