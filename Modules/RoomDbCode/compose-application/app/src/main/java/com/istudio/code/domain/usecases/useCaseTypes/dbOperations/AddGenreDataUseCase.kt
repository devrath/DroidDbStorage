package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Genre
import javax.inject.Inject

class AddGenreDataUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl,
) {

    operator fun invoke(bookCategories: Array<String>): Result<Boolean> {
        try {
            val genreList: ArrayList<Genre> = arrayListOf()
            bookCategories.forEach { item ->
                genreList.add(Genre(name = item))
            }
            // Insert into the database
            appRepositoryImpl.addGenres(genreList)
            // Return as success
            return Result.success(true)
        } catch (ex: Exception) {
            // Return as failure
            return Result.failure(ex)
        }
    }

}