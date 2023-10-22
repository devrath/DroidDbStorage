package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.core.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.code.core.platform.coroutines.usecase.SuspendUseCase
import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.entities.input.AddBookAllInputs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import com.istudio.code.core.platform.coroutines.usecase.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AddBookUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val appRepositoryImpl: AppRepositoryImpl,
) : SuspendUseCase<AddBookAllInputs, Result<Boolean>>(dispatcher) {

    override suspend fun execute(parameters: AddBookAllInputs): Result<Boolean> =

        suspendCancellableCoroutine { coroutine ->
            val genreId = appRepositoryImpl.getGenres().firstOrNull {
                it.name == parameters.category
            }?.id

            if ((parameters.title.isNotBlank()) && (parameters.description.isNotBlank()) && (!genreId.isNullOrBlank())) {
                val book =
                    Book(genreId = genreId, name = parameters.title, description = parameters.description)
                // Add the book to the database

                CoroutineScope(dispatcher).launch {
                    appRepositoryImpl.addBook(book)
                    coroutine.resume(Result.Success(true))
                }
            } else {
                coroutine.resume(Result.Success(false))
            }
        }

}