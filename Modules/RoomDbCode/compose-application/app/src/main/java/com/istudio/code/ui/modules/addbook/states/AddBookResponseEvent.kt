package com.istudio.code.ui.modules.addbook.states

sealed class AddBookResponseEvent {
    object AddBookSuccess : AddBookResponseEvent()
    //data class ShowSnackbar(val message: String) : AddBookResponseEvent()
}
