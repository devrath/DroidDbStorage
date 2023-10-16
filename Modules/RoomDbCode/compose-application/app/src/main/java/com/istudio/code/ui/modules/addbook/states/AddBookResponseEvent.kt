package com.istudio.code.ui.modules.addbook.states

sealed class AddBookResponseEvent {
    object AddBookSuccess : AddBookResponseEvent()
    object TitleFieldError : AddBookResponseEvent()
    object DescriptionFieldError : AddBookResponseEvent()
    object CategoryFieldError : AddBookResponseEvent()
    //data class ShowSnackbar(val message: String) : AddBookResponseEvent()
}
