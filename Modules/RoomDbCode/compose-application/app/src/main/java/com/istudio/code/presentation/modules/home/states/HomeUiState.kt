package com.istudio.code.presentation.modules.home.states

import com.istudio.code.domain.database.models.Book

data class HomeUiState(
    val books: List<Book> = emptyList(),

    val launchedEffectState: Boolean = false
)
