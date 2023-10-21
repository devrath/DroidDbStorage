package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Review
import javax.inject.Inject

class AddReviewUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) {

    operator fun invoke(review : Review) : Result<Boolean>{
        try {
            appRepositoryImpl.addReview(review)
            return Result.success(true)
        }catch (ex:Exception){
            return Result.failure(ex)
        }
    }

}