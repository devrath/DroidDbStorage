package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.core.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.code.core.platform.coroutines.usecase.SuspendUseCase
import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Book
import com.istudio.code.core.platform.coroutines.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DeleteBookUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val appRepositoryImpl: AppRepositoryImpl
) : SuspendUseCase<Book, Result<Boolean>>(dispatcher) {
    override suspend fun execute(book: Book): Result<Boolean> =
        suspendCancellableCoroutine { coroutine ->
            try {
                CoroutineScope(dispatcher).launch {
                    appRepositoryImpl.removeBook(book)
                    coroutine.resume(Result.Success(true))
                }
            }catch (ex:Exception){
                coroutine.resume(Result.Error(ex))
            }
        }

}