package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.relations.BookAndGenre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl,
) {
    operator fun invoke(): Result<Flow<List<BookAndGenre>>> {
        try {
            // Return as success
            return Result.success(appRepositoryImpl.getBooks())
        } catch (ex: Exception) {
            // Return as failure
            return Result.failure(ex)
        }
    }
}