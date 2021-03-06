package io.github.cbinarycastle.betty.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<P, R>(private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: P): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(params).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            onError(
                exception = e,
                params = params
            )
            Result.Error(e)
        }
    }

    protected open fun onError(exception: Exception, params: P) {}

    protected abstract suspend fun execute(params: P): R
}