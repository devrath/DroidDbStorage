package com.istudio.code.presentation.modules.home.states.myBooks

sealed class MyBooksEvent {
    object RefreshData : MyBooksEvent()
    data class ShowSnackBar(val message: String) : MyBooksEvent()
}
