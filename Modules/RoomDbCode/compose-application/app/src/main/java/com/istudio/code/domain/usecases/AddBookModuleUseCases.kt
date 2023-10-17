package com.istudio.code.domain.usecases

import com.istudio.code.domain.usecases.validateAddBook.ValidateAddBookUseCase

data class AddBookModuleUseCases(
    // --> ***************** VALIDATIONS *****************
    val validateLogin: ValidateAddBookUseCase,
    // --> ***************** VALIDATIONS *****************
)
