package com.istudio.code.domain.usecases.useCaseTypes.validationOperations

import com.istudio.code.R
import com.istudio.code.domain.ValidationResult
import com.istudio.code.domain.entities.input.AddBookDescriptionInput
import com.istudio.code.core.platform.uiEvent.UiText
import javax.inject.Inject

class ValidateDescriptionInputUseCase  @Inject constructor() {

    operator fun invoke(
        input: AddBookDescriptionInput
    ): Result<ValidationResult> {
        // Check if description is empty
        return try {
            val result = initiateValidation(input)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun initiateValidation(input: AddBookDescriptionInput): ValidationResult {
        return if(input.description.isEmpty()){
            ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.str_err_description_validation_failed)
            )
        }else{
            ValidationResult(
                successful = true,
            )
        }
    }

}