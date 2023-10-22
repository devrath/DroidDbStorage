package com.istudio.code.core.platform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel<in INPUT_FROM_VIEW> : ViewModel() {

    internal val tag = this.javaClass.simpleName

    abstract fun setupPrerequisites(params: INPUT_FROM_VIEW)

    internal inline fun ViewModel.launchUseCase(
        handler: CoroutineExceptionHandler,
        crossinline block: suspend () -> Unit
    ): Job = viewModelScope.launch(handler) { block() }

    internal inline fun ViewModel.catchException(crossinline block: (exception: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            block(exception)
        }
    }
}

internal inline fun ifNoInternet(crossinline block: () -> Unit) {
    block()
}