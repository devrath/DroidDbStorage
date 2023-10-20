package com.istudio.code.presentation.modules.addReview.states


sealed class AddReviewViewEvent{
    //object AddBookViewClick : AddReviewViewEvent()


    data class SetBookTitle(val title: String) : AddReviewViewEvent()
    data class SetRating(val rating: String) : AddReviewViewEvent()
    data class SetReviewNotes(val rating: String) : AddReviewViewEvent()


    object GetBooksList : AddReviewViewEvent()

    data class SetBooksList(val title: List<String>) : AddReviewViewEvent()
    data class SetRatingsList(val ratinglist: List<Int>) : AddReviewViewEvent()
    data class SetBookListExpandedState(val isExpanded: Boolean) : AddReviewViewEvent()
    data class SetRatingsListExpandedState(val isExpanded: Boolean) : AddReviewViewEvent()

}
