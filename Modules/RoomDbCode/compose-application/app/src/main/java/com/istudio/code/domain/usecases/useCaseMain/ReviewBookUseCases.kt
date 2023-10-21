package com.istudio.code.domain.usecases.useCaseMain

import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddReviewUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.DeleteReviewUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateBookSelectedUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateRatingSelectionUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateReviewNotesUseCase

data class ReviewBookUseCases(

    // --> ***************** VALIDATIONS *****************
    val validateBookSelectedUseCase : ValidateBookSelectedUseCase,
    val validateRatingSelectionUseCase : ValidateRatingSelectionUseCase,
    val validateReviewNotesUseCase : ValidateReviewNotesUseCase,
    // --> ***************** VALIDATIONS *****************

    // --> ********** REPOSITORY-OPERATIONS **************
    val getBooksUseCase : GetBooksUseCase,
    val deleteReviewUseCase : DeleteReviewUseCase,
    val addReviewUseCase : AddReviewUseCase,
    // --> ********** REPOSITORY-OPERATIONS **************

)
