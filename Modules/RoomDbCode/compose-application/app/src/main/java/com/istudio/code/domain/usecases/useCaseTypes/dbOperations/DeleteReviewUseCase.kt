package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.Review
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) {
    operator fun invoke(review: Review) : Result<Boolean>{
        try{
            appRepositoryImpl.removeReview(review)
            return Result.success(true)
        } catch (ex: Exception) {
            return Result.failure(exception = ex)
        }
    }
}