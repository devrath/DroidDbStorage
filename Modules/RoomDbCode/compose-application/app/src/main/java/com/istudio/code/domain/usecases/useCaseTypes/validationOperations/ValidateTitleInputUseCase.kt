package com.istudio.code.domain.usecases.useCaseTypes.validationOperations

import com.istudio.code.R
import com.istudio.code.domain.ValidationResult
import com.istudio.code.domain.entities.input.AddBookTitleInput
import com.istudio.code.core.platform.uiEvent.UiText
import javax.inject.Inject

class ValidateTitleInputUseCase @Inject constructor() {

    operator fun invoke(
        input: AddBookTitleInput
    ): Result<ValidationResult> {
        // Check if title is empty
        return try {
            val result = initiateValidation(input)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun initiateValidation(input: AddBookTitleInput): ValidationResult {
        return if(input.title.isEmpty()){
            ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.str_err_title_validation_failed)
            )
        }else{
            ValidationResult(
                successful = true,
            )
        }
    }

}