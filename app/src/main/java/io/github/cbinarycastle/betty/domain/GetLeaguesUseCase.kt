package io.github.cbinarycastle.betty.domain

import io.github.cbinarycastle.betty.di.IoDispatcher
import io.github.cbinarycastle.betty.entity.League
import io.github.cbinarycastle.betty.event.Event
import io.github.cbinarycastle.betty.event.EventLogger
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val eventLogger: EventLogger,
    private val repository: LeagueRepository,
) : UseCase<Unit, List<League>>(dispatcher) {

    override suspend fun execute(params: Unit): List<League> {
        return repository.getLeagues()
    }

    override fun onError(exception: Exception, params: Unit) {
        eventLogger.logEvent(Event.MatchesLeaguesLoadFailed())
    }
}