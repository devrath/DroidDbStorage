package com.istudio.code.domain.database.models

data class BookItem(
    val bookId: String,
    val name: String,
    var isSelected: Boolean = false
)