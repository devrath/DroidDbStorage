package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.relations.ReviewAndBook
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl,
) {
    operator fun invoke(): Result<List<ReviewAndBook>> {
        try {
            // Return as success
            return Result.success(appRepositoryImpl.getAllReviews())
        } catch (ex: Exception) {
            // Return as failure
            return Result.failure(ex)
        }
    }
}