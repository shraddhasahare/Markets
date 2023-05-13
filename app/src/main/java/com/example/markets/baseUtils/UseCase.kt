package com.example.markets.baseUtils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .flowOn(coroutineDispatcher)

    protected abstract suspend fun execute(parameters: P): Flow<Result<R>>
}
