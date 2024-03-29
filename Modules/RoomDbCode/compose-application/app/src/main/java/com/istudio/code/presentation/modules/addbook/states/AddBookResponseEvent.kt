package com.istudio.code.presentation.modules.addbook.states

sealed class AddBookResponseEvent {
    object AddBookSuccess : AddBookResponseEvent()
    object TitleFieldError : AddBookResponseEvent()
    object DescriptionFieldError : AddBookResponseEvent()
    object CategoryFieldError : AddBookResponseEvent()
    data class ShowSnackBar(val message: String) : AddBookResponseEvent()
}
