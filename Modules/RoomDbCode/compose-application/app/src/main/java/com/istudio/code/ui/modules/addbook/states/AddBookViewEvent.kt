package com.istudio.code.ui.modules.addbook.states

sealed class AddBookViewEvent {
    object AddBookViewClick : AddBookViewEvent()
    data class SetTitle(val title: String) : AddBookViewEvent()
    data class SetDescription(val description: String) : AddBookViewEvent()
    data class SetCategory(val category: String) : AddBookViewEvent()
}
