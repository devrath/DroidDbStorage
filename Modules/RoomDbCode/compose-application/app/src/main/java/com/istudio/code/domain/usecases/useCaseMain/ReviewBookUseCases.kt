package com.istudio.code.domain.usecases.useCaseMain

import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateBookSelectedUseCase

data class ReviewBookUseCases(

    // --> ***************** VALIDATIONS *****************
    val validateBookSelectedUseCase : ValidateBookSelectedUseCase,
    // --> ***************** VALIDATIONS *****************

    // --> ********** REPOSITORY-OPERATIONS **************
    val getBooksUseCase : GetBooksUseCase
    // --> ********** REPOSITORY-OPERATIONS **************

)
