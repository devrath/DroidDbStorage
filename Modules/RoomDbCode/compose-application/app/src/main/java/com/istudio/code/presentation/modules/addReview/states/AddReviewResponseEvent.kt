package com.istudio.code.presentation.modules.addReview.states


sealed class AddReviewResponseEvent {

    //object CategoryFieldError : AddReviewResponseEvent()
    data class ShowSnackBar(val message: String) : AddReviewResponseEvent()
    object ReviewAddIsSuccessful : AddReviewResponseEvent()
    /*object BookTitleValidationFailed : AddReviewResponseEvent()
    object BookRatingValidationFailed : AddReviewResponseEvent()
    object BookReviewValidationFailed : AddReviewResponseEvent()*/

}
