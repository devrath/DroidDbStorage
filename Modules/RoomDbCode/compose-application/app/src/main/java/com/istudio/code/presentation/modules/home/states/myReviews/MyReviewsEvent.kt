package com.istudio.code.presentation.modules.home.states.myReviews

sealed class MyReviewsEvent  {
    object RefreshData : MyReviewsEvent()
    data class ShowSnackBar(val message: String) : MyReviewsEvent()
}

