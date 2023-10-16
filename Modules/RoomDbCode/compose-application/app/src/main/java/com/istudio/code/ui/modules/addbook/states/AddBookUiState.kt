package com.istudio.code.ui.modules.addbook.states

import androidx.annotation.StringRes

data class AddBookUiState(
    val title: String = "",
    val description: String = "",
    val category: String = "",

    val isExpanded: Boolean = false,
    val isTitleError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isCategoryError: Boolean = false,
    val launchedEffectState: Boolean = false,
)
