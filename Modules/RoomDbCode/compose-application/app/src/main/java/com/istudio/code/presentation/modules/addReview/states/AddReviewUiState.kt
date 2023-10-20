package com.istudio.code.presentation.modules.addReview.states

data class AddReviewUiState(
    val bookTitle : String = "",
    val rating : Int = 0,
    val reviewNotes : String = "",


    val launchedEffectState: Boolean = false,
    val isBookListExpanded: Boolean = false,

)
