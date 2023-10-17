package com.istudio.code.domain

import com.istudio.code.core.platform.uiEvent.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText.StringResource? = null
)
