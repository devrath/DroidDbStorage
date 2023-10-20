package com.istudio.code.presentation.modules.addReview.states


sealed class AddReviewResponseEvent {

    //object CategoryFieldError : AddReviewResponseEvent()
    data class ShowSnackBar(val message: String) : AddReviewResponseEvent()


}
