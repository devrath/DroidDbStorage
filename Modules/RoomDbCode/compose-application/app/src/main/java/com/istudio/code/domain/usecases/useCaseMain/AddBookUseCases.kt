package com.istudio.code.domain.usecases.useCaseMain

import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.DeleteBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksAndGenreUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateTitleInputUseCase

data class AddBookUseCases(
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
    val getBooksAndGenreUseCase: GetBooksAndGenreUseCase,
    val deleteBookUseCase: DeleteBookUseCase
    // --> ********** REPOSITORY-OPERATIONS **************
)
