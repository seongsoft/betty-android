package io.github.cbinarycastle.betty.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.betty.domain.GetMatchOverallsUseCase
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.event.Event
import io.github.cbinarycastle.betty.event.EventLogger
import io.github.cbinarycastle.betty.ui.match.list.insertSeparators
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMatchesViewModel @Inject constructor(
    getMatchOverallsUseCase: GetMatchOverallsUseCase,
    private val eventLogger: EventLogger,
) : ViewModel() {

    private val refreshSignal = MutableSharedFlow<Unit>()
    private val loadDataSignal = flow {
        emit(Unit)
        emitAll(refreshSignal)
    }

    private val _keyword = MutableStateFlow<String?>(null)
    val keyword = _keyword.asStateFlow()

    val matchOveralls = keyword
        .filter { it != null }
        .combine(loadDataSignal) { keyword, _ ->
            GetMatchOverallsUseCase.Params(keyword = keyword)
        }
        .flatMapLatest { params ->
            getMatchOverallsUseCase(params)
                .filter { it is Result.Success }
                .map { (it as Result.Success).data }
                .mapLatest { it.insertSeparators() }
        }
        .cachedIn(viewModelScope)

    fun refresh() {
        viewModelScope.launch {
            refreshSignal.emit(Unit)
        }
    }

    fun selectMatch(matchOverall: MatchOverall) {
        eventLogger.logEvent(
            Event.MatchesMatchItemClick(
                matchId = matchOverall.id,
                matchAt = matchOverall.matchAt,
                homeTeamName = matchOverall.homeTeam.displayName,
                awayTeamName = matchOverall.awayTeam.displayName,
            )
        )
    }

    fun search(keyword: String) {
        _keyword.value = keyword
    }
}