package com.istudio.code.core.platform.coroutines.usecase

import androidx.lifecycle.MutableLiveData
import com.istudio.code.core.platform.coroutines.usecase.Result.Success
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$value]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * [Success.value] if [Result] is of type [Success]
 */
fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.value ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Success)?.value

/**
 * Updates value of [liveData] if [Result] is of type [Success]
 */
inline fun <reified T> Result<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is Success) {
        liveData.value = value
    }
}

/**
 * Updates value of [stateFlow] if [Result] is of type [Success]
 */
inline fun <reified T> Result<T>.flowOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is Success) {
        stateFlow.value = value
    }
}