package io.github.cbinarycastle.macao.ui.match.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.macao.domain.GetLeaguesUseCase
import io.github.cbinarycastle.macao.domain.GetMatchOverallsUseCase
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.domain.data
import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.event.Event
import io.github.cbinarycastle.macao.event.EventLogger
import io.github.cbinarycastle.macao.util.WhileViewSubscribed
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatchOverallsUseCase: GetMatchOverallsUseCase,
    getLeaguesUseCase: GetLeaguesUseCase,
    private val eventLogger: EventLogger,
) : ViewModel() {

    private val refreshSignal = MutableSharedFlow<Unit>()
    private val loadDataSignal = flow {
        emit(Unit)
        emitAll(refreshSignal)
    }

    val leagues = loadDataSignal.map {
        when (val result = getLeaguesUseCase(Unit)) {
            is Result.Success -> Result.Success(
                listOf(LeagueFilterModel.All) + result.data.map {
                    LeagueFilterModel.League(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl,
                    )
                }
            )
            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }.stateIn(viewModelScope, WhileViewSubscribed, Result.Loading)

    private val _selectedLeagueIndex = MutableStateFlow(0)
    val selectedLeagueIndex = _selectedLeagueIndex.asStateFlow()

    val matchOveralls = loadDataSignal
        .combine(selectedLeagueIndex) { _, leagueIndex -> leagueIndex }
        .map { leagues.value.data?.get(it) }
        .flatMapLatest { leagueFilter ->
            getMatchOverallsUseCase(
                GetMatchOverallsUseCase.Params(leagueId = leagueFilter?.id)
            )
                .filter { it is Result.Success }
                .map { (it as Result.Success).data }
                .mapLatest { it.insertSeparators() }
        }
        .cachedIn(viewModelScope)

    private val matchOverallsLoading = MutableStateFlow(false)

    val isRefreshing = leagues.combine(matchOverallsLoading) { leagues, matchOverallsLoading ->
        leagues is Result.Loading && matchOverallsLoading
    }

    fun refresh() {
        viewModelScope.launch {
            refreshSignal.emit(Unit)
        }
    }

    fun updateMatchOverallsLoading(loadState: LoadState) {
        matchOverallsLoading.value = loadState is LoadState.Loading
    }

    fun selectLeague(index: Int) {
        _selectedLeagueIndex.value = index
        leagues.value.data?.get(index)?.let {
            eventLogger.logEvent(
                Event.MatchesLeagueFilterClick(leagueName = it.name)
            )
        }
    }

    fun selectMatch(matchOverall: MatchOverall) {
        eventLogger.logEvent(
            Event.MatchesMatchItemClick(
                matchId = matchOverall.id,
                matchAt = matchOverall.matchAt,
                homeTeamName = matchOverall.homeTeam.name,
                awayTeamName = matchOverall.awayTeam.name,
            )
        )
    }
}