package io.github.cbinarycastle.macao.domain

import io.github.cbinarycastle.macao.di.IoDispatcher
import io.github.cbinarycastle.macao.entity.MatchOverall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMatchOverallsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MatchOverallsRepository,
) : UseCase<Unit, List<MatchOverall>>(dispatcher) {

    override suspend fun execute(params: Unit): List<MatchOverall> {
        return repository.getMatchOveralls()
    }
}