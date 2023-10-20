package com.istudio.code.domain.usecases.useCaseTypes.validationOperations

import com.istudio.code.R
import com.istudio.code.domain.ValidationResult
import com.istudio.code.domain.entities.input.AddBookAllInputs
import com.istudio.code.core.platform.uiEvent.UiText
import javax.inject.Inject

class ValidateAllInputsUseCase @Inject constructor(){

    private enum class VALIDATIONS { TITLE, DESCRIPTION, CATEGORY }

    operator fun invoke(
        input: AddBookAllInputs
    ): Result<ValidationResult> {
        return try {
            val result = initiateValidation(input)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun initiateValidation(input: AddBookAllInputs): ValidationResult {

        return if (validateAddBookAction(input)) {
            ValidationResult(
                successful = true,
            )
        }else{
            ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.str_err_validations_failed)
            )
        }
    }


    private fun validateFields(input: AddBookAllInputs, widget: VALIDATIONS) = when (widget) {
        // Title validation
        VALIDATIONS.TITLE -> input.title.isNotEmpty()
        // Description validation
        VALIDATIONS.DESCRIPTION -> input.description.isNotEmpty()
        // Category validation
        VALIDATIONS.CATEGORY -> input.category.isNotEmpty()
    }

    private fun validateAddBookAction(input: AddBookAllInputs): Boolean {
        return validateFields(input, VALIDATIONS.TITLE) &&
                validateFields(input, VALIDATIONS.DESCRIPTION) &&
                validateFields(input, VALIDATIONS.CATEGORY)
    }

}