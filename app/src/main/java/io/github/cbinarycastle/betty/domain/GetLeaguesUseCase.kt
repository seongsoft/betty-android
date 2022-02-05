package io.github.cbinarycastle.betty.domain

import io.github.cbinarycastle.betty.di.IoDispatcher
import io.github.cbinarycastle.betty.entity.League
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: LeagueRepository,
) : UseCase<Unit, List<League>>(dispatcher) {

    override suspend fun execute(params: Unit): List<League> = repository.getLeagues()
}