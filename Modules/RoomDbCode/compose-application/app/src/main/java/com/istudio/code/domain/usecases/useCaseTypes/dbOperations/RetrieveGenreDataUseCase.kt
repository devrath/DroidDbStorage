package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import javax.inject.Inject

class RetrieveGenreDataUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl,
) {
    operator fun invoke() : Result<List<String>>{
        try {
            // Genre contains the all the columns in table
            // We shall use map operator to get just the names as the list
            val genreName : List<String> = appRepositoryImpl.getGenres().map {
                it.name
            }
            return  Result.success(genreName)
        }catch (ex:Exception){
            return Result.failure(ex)
        }

    }

}