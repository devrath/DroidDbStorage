package com.istudio.code.presentation.modules.home.states.myBooks

sealed class MyBooksEvent {
    data class ShowSnackBar(val message: String) : MyBooksEvent()
}
