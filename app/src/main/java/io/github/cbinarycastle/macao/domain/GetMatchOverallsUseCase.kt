package io.github.cbinarycastle.macao.domain

import androidx.paging.PagingData
import io.github.cbinarycastle.macao.di.IoDispatcher
import io.github.cbinarycastle.macao.entity.MatchOverall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMatchOverallsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MatchOverallsRepository,
) : FlowUseCase<Unit, PagingData<MatchOverall>>(dispatcher) {

    override fun execute(params: Unit): Flow<Result<PagingData<MatchOverall>>> {
        return repository.getMatchOveralls()
            .map { Result.Success(it) }
    }
}