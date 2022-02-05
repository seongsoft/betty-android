package io.github.cbinarycastle.macao.ui.match.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.macao.domain.GetMatchDetailsUseCase
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.event.Event
import io.github.cbinarycastle.macao.event.EventLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    getMatchDetailsUseCase: GetMatchDetailsUseCase,
    private val eventLogger: EventLogger,
) : ViewModel() {

    private val matchId = MutableStateFlow<Long?>(null)

    val matchDetails = matchId
        .filterNotNull()
        .mapLatest { matchId ->
            getMatchDetailsUseCase(GetMatchDetailsUseCase.Params(matchId)).also { result ->
                if (result is Result.Error) {
                    eventLogger.logEvent(Event.MatchDetailsLoadFailed(matchId = matchId))
                }
            }
        }

    fun setMatchId(matchId: Long) {
        this.matchId.value = matchId
    }

    fun onTabSelected(tabName: String) {
        eventLogger.logEvent(
            Event.MatchDetailsTabClick(tabName = tabName)
        )
    }
}