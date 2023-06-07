package com.asad.core.network

import com.asad.core.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class PagingFlowUseCase<in P, out R>() {
    suspend operator fun invoke(parameters: P? = null): Flow<R> = execute(parameters)
        .catch { e ->
            Resource.Error(
                Throwable(
                    message = e.localizedMessage
                )
            )
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<R>
}