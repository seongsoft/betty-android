package io.github.cbinarycastle.macao.domain

import io.github.cbinarycastle.macao.di.IoDispatcher
import io.github.cbinarycastle.macao.entity.League
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: LeagueRepository,
) : UseCase<Unit, List<League>>(dispatcher) {

    override suspend fun execute(params: Unit): List<League> = repository.getLeagues()
}