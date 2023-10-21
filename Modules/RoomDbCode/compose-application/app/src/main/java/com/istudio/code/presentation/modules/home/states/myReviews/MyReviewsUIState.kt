package com.istudio.code.presentation.modules.home.states.myReviews

import com.istudio.code.domain.database.models.Review

data class MyReviewsUIState(
    val reviews: List<Review> = emptyList(),
    var review : Review? = null,
    val launchedEffectState: Boolean = false,
)

