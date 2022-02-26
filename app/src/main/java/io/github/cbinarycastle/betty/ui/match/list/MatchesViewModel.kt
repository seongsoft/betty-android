package io.github.cbinarycastle.betty.ui.match.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.betty.domain.GetLeaguesUseCase
import io.github.cbinarycastle.betty.domain.GetMatchOverallsUseCase
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.domain.data
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.event.Event
import io.github.cbinarycastle.betty.event.EventLogger
import io.github.cbinarycastle.betty.util.WhileViewSubscribed
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
        getLeaguesUseCase(Unit).run {
            when (this) {
                is Result.Success -> Result.Success(
                    listOf(LeagueFilterModel.All) + data.map { league ->
                        LeagueFilterModel.League(
                            id = league.id,
                            name = league.name,
                            imageUrl = league.imageUrl,
                        )
                    }
                )
                is Result.Error -> Result.Error(exception)
                Result.Loading -> Result.Loading
            }
        }
    }.stateIn(viewModelScope, WhileViewSubscribed, Result.Loading)

    private val _selectedLeagueIndex = MutableStateFlow(0)
    val selectedLeagueIndex = _selectedLeagueIndex.asStateFlow()

    private val _keyword = MutableStateFlow<String?>(null)
    val keyword = _keyword.asStateFlow()

    val matchOveralls = combine(
        loadDataSignal, selectedLeagueIndex, keyword
    ) { _, leagueIndex, keyword ->
        val leagueFilter = leagues.value.data?.get(leagueIndex)
        GetMatchOverallsUseCase.Params(
            leagueId = leagueFilter?.id,
            leagueName = leagueFilter?.name,
            keyword = keyword,
        )
    }
        .flatMapLatest { params ->
            getMatchOverallsUseCase(params)
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

    fun search(keyword: String) {
        _keyword.value = keyword
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
}