package com.istudio.code.presentation.modules.addbook.states

sealed class AddBookViewEvent {
    object AddBookViewClick : AddBookViewEvent()
    data class SetTitle(val title: String) : AddBookViewEvent()
    data class SetDescription(val description: String) : AddBookViewEvent()
    data class SetCategory(val category: String) : AddBookViewEvent()


    data class SetIsExpanded(val isExpanded: Boolean) : AddBookViewEvent()
    data class SetIsTitleError(val isTitleError: Boolean) : AddBookViewEvent()
    data class SetIsDescriptionError(val isDescriptionError: Boolean) : AddBookViewEvent()
    data class SetIsCategoryError(val isCategoryError: Boolean) : AddBookViewEvent()
    data class SetLaunchedEffectState(val launchedEffectState: Boolean) : AddBookViewEvent()
}
