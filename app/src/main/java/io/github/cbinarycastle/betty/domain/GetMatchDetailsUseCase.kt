package io.github.cbinarycastle.betty.domain

import io.github.cbinarycastle.betty.di.IoDispatcher
import io.github.cbinarycastle.betty.entity.MatchDetails
import io.github.cbinarycastle.betty.event.Event
import io.github.cbinarycastle.betty.event.EventLogger
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMatchDetailsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val eventLogger: EventLogger,
    private val repository: MatchDetailsRepository,
) : UseCase<GetMatchDetailsUseCase.Params, MatchDetails>(dispatcher) {

    override suspend fun execute(params: Params): MatchDetails {
        return repository.getMatchDetails(params.matchId)
    }

    override fun onError(exception: Exception, params: Params) {
        eventLogger.logEvent(Event.MatchDetailsLoadFailed(params.matchId))
    }

    data class Params(val matchId: Long)
}