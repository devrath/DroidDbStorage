package com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook

import com.istudio.code.R
import com.istudio.code.domain.ValidationResult
import com.istudio.code.domain.entities.input.AddBookCategoryInput
import com.istudio.code.core.platform.uiEvent.UiText
import javax.inject.Inject

class ValidateCategoryInputUseCase   @Inject constructor() {

    operator fun invoke(
        input: AddBookCategoryInput
    ): Result<ValidationResult> {
        // Check if description is empty
        return try {
            val result = initiateValidation(input)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun initiateValidation(input: AddBookCategoryInput): ValidationResult {
        return if(input.category.isEmpty()){
            ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.str_err_category_validation_failed)
            )
        }else{
            ValidationResult(
                successful = true,
            )
        }
    }

}