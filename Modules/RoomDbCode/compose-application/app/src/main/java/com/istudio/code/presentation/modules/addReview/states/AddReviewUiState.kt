package com.istudio.code.presentation.modules.addReview.states

import com.istudio.code.domain.database.models.Book

data class AddReviewUiState(
    // <------> Values stored for operation <------------>
    val bookTitle : String = "",
    val rating : String = "",
    val reviewNotes : String = "",
    // <------> Values stored for operation <------------>

    // <-------------> Other values stored <------------->
    val ratingsList : List<Int> = listOf(),
    val booksList : List<Book> = listOf(),
    val launchedEffectState: Boolean = false,
    val isBookListExpanded: Boolean = false,
    val isRatingsListExpanded: Boolean = false,
    val book: Book? = null,
    // <-------------> Other values stored <------------->

    // <-------------> Error states <-------------------->
    val isBookError: Boolean = false,
    val isRatingError: Boolean = false,
    val isReviewError: Boolean = false,
    // <-------------> Error states <-------------------->

)
