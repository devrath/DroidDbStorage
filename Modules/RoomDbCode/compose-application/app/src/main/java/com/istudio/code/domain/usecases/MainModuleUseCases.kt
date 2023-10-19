package com.istudio.code.domain.usecases

import com.istudio.code.domain.usecases.dbOperations.AddBookUseCase
import com.istudio.code.domain.usecases.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.dbOperations.DeleteBookUseCase
import com.istudio.code.domain.usecases.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateTitleInputUseCase

data class MainModuleUseCases(
    // --> ***************** VALIDATIONS *****************
    val validateAllInputs: ValidateAllInputsUseCase,
    val validateTitle: ValidateTitleInputUseCase,
    val validateDescription: ValidateDescriptionInputUseCase,
    val validateCategory: ValidateCategoryInputUseCase,
    // --> ***************** VALIDATIONS *****************
    // --> ********** REPOSITORY-OPERATIONS **************
    val addGenreDataUseCase: AddGenreDataUseCase,
    val retrieveGenreDataUseCase: RetrieveGenreDataUseCase,
    val addBookUseCase: AddBookUseCase,
    val getBooksUseCase: GetBooksUseCase,
    val deleteBookUseCase: DeleteBookUseCase
    // --> ********** REPOSITORY-OPERATIONS **************
)
