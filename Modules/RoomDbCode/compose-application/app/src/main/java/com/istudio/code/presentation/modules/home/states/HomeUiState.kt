package com.istudio.code.presentation.modules.home.states

import androidx.compose.runtime.mutableStateOf
import com.istudio.code.domain.database.models.relations.BookAndGenre

data class HomeUiState(
    val books: List<BookAndGenre> = emptyList(),
)
