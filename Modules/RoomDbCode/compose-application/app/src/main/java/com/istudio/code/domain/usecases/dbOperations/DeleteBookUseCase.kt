package com.istudio.code.domain.usecases.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Book
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) {
    operator fun invoke(book: Book) : Result<Boolean>{
        try{
            appRepositoryImpl.removeBook(book)
            return Result.success(true)
        } catch (ex: Exception) {
            return Result.failure(exception = ex)
        }
    }
}