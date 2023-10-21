package com.istudio.code.presentation.modules.home.states.myReviews

import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.database.models.relations.ReviewAndBook

data class MyReviewsUIState(
    val reviews: List<ReviewAndBook> = emptyList(),
    var review : Review? = null,
    val launchedEffectState: Boolean = false,
)

