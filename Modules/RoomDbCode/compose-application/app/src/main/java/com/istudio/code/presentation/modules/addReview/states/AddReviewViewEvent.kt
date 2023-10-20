package com.istudio.code.presentation.modules.addReview.states


sealed class AddReviewViewEvent{
    //object AddBookViewClick : AddReviewViewEvent()
    data class SetBooKTitle(val title: List<String>) : AddReviewViewEvent()
    data class SetRating(val rating: Int) : AddReviewViewEvent()
    data class SetReviewNotes(val rating: String) : AddReviewViewEvent()
}
