package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.core.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.code.core.platform.coroutines.usecase.SuspendUseCase
import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.core.platform.coroutines.usecase.Result
import com.istudio.code.domain.database.models.Review
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DeleteReviewUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val appRepositoryImpl: AppRepositoryImpl
) : SuspendUseCase<Review,Result<Boolean>>(dispatcher) {
    override suspend fun execute(review: Review): Result<Boolean> =
        suspendCancellableCoroutine { coroutine ->
            try {
                CoroutineScope(dispatcher).launch {
                    appRepositoryImpl.removeReview(review)
                    coroutine.resume(Result.Success(true))
                }
            }catch (ex:Exception){
                coroutine.resume(Result.Error(ex))
            }
        }
}