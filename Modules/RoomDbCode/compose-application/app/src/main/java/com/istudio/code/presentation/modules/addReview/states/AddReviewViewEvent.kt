package com.istudio.code.presentation.modules.addReview.states


sealed class AddReviewViewEvent{

    data class SetBookTitle(val title: String) : AddReviewViewEvent()
    data class SetRating(val rating: String) : AddReviewViewEvent()
    data class SetReviewNotes(val reviewNotes: String) : AddReviewViewEvent()


    data class SetRatingsList(val ratinglist: List<Int>) : AddReviewViewEvent()
    data class SetBookListExpandedState(val isExpanded: Boolean) : AddReviewViewEvent()
    data class SetRatingsListExpandedState(val isExpanded: Boolean) : AddReviewViewEvent()


    object GetBooksList : AddReviewViewEvent()
    object PerformAction : AddReviewViewEvent()


    data class SetRatingErrorState(val isError: Boolean) : AddReviewViewEvent()
    data class SetBookErrorState(val isError: Boolean) : AddReviewViewEvent()
    data class SetReviewErrorState(val isError: Boolean) : AddReviewViewEvent()

}
