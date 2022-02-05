package io.github.cbinarycastle.betty.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

abstract class FlowUseCase<P, R>(
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(params: P): Flow<Result<R>> {
        return execute(params)
            .catch {
                Timber.e(it)
                Result.Error(it as Exception)
            }
            .flowOn(dispatcher)
    }

    protected abstract fun execute(params: P): Flow<Result<R>>
}