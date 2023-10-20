package com.istudio.code.domain.usecases.useCaseMain

import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.DeleteBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateTitleInputUseCase

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
    val addBookUseCase: AddBookUseCase,
    val getBooksUseCase: GetBooksUseCase,
    val deleteBookUseCase: DeleteBookUseCase
    // --> ********** REPOSITORY-OPERATIONS **************
)
