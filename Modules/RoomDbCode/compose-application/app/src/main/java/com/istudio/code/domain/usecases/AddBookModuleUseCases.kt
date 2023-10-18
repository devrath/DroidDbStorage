package com.istudio.code.domain.usecases

import com.istudio.code.domain.usecases.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateTitleInputUseCase

data class AddBookModuleUseCases(
    // --> ***************** VALIDATIONS *****************
    val validateAllInputs: ValidateAllInputsUseCase,
    val validateTitle: ValidateTitleInputUseCase,
    val validateDescription: ValidateDescriptionInputUseCase,
    val validateCategory: ValidateCategoryInputUseCase,
    // --> ***************** VALIDATIONS *****************
    // --> ********** REPOSITORY-OPERATIONS **************
    val addGenreDataUseCase: AddGenreDataUseCase,
    val retrieveGenreDataUseCase: RetrieveGenreDataUseCase,
    // --> ********** REPOSITORY-OPERATIONS **************
)
