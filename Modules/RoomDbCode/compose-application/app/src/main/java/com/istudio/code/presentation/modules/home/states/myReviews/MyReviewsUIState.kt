package com.istudio.code.presentation.modules.home.states.myReviews

import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Review

data class MyReviewsUIState(
    val books: List<Review> = emptyList(),
    var book : Book? = null,
    val launchedEffectState: Boolean = false,
)

