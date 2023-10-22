package com.istudio.code.domain.usecases.useCaseTypes.dbOperations

import com.istudio.code.core.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.code.core.platform.coroutines.usecase.Result
import com.istudio.code.core.platform.coroutines.usecase.SuspendUseCase
import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.database.models.relations.ReviewAndBook
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class GetReviewsUseCase @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val appRepositoryImpl: AppRepositoryImpl,
) : SuspendUseCase<HashMap<String, String>, Result<Flow<List<ReviewAndBook>>>>(dispatcher) {

    override suspend fun execute(parameters: HashMap<String, String>): Result<Flow<List<ReviewAndBook>>> =
        suspendCancellableCoroutine { coroutine ->
            try {
                CoroutineScope(dispatcher).launch {
                    val result = appRepositoryImpl.getAllReviews()
                    coroutine.resume(Result.Success(result))
                }
            } catch (ex: Exception) {
                coroutine.resume(Result.Error(ex))
            }
        }
}