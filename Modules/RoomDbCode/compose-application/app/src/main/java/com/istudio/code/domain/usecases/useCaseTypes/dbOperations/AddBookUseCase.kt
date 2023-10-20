package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.entities.input.AddBookAllInputs
import javax.inject.Inject

class AddBookUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl,
) {
    operator fun invoke(input: AddBookAllInputs): Result<Boolean> {

        try {
            // We map the Id from genre table to book table
            val genreId = appRepositoryImpl.getGenres().firstOrNull {
                it.name == input.category
            }?.id

            if ((input.title.isNotBlank()) && (input.description.isNotBlank()) && (!genreId.isNullOrBlank())) {
                val book =
                    Book(genreId = genreId, name = input.title, description = input.description)
                // Add the book to the database
                appRepositoryImpl.addBook(book)
                return Result.success(value = true)
            } else {
                return Result.success(value = false)
            }
        } catch (ex: Exception) {
            return Result.failure(exception = ex)
        }

    }

}