package io.github.cbinarycastle.betty.ui.match.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.betty.domain.GetMatchDetailsUseCase
import io.github.cbinarycastle.betty.event.Event
import io.github.cbinarycastle.betty.event.EventLogger
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
        .mapLatest { getMatchDetailsUseCase(GetMatchDetailsUseCase.Params(it)) }

    fun setMatchId(matchId: Long) {
        this.matchId.value = matchId
    }

    fun onTabSelected(tabName: String) {
        eventLogger.logEvent(
            Event.MatchDetailsTabClick(tabName)
        )
    }
}