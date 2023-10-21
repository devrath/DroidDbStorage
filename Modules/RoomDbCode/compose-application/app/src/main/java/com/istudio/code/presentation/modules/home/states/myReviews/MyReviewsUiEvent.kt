package com.istudio.code.presentation.modules.home.states.myReviews

import com.istudio.code.domain.database.models.Review

sealed class MyReviewsUiEvent {
    object GetMyReviews : MyReviewsUiEvent()
    data class DeleteReview(val book: Review) : MyReviewsUiEvent()
    data class ConfirmDeleteReview(val book: Review) : MyReviewsUiEvent()

}

