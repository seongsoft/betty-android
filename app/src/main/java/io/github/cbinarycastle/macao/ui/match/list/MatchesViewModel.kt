package io.github.cbinarycastle.macao.ui.match.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.macao.domain.GetMatchOverallsUseCase
import io.github.cbinarycastle.macao.domain.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatchOverallsUseCase: GetMatchOverallsUseCase,
) : ViewModel() {

    private val _leagues = MutableStateFlow(
        listOf(
            "All",
            "Premier League",
            "Bundesliga",
            "LaLiga",
            "Serie A"
        )
    )
    val leagues = _leagues.asStateFlow()

    private val _selectedLeagueIndex = MutableStateFlow(0)
    val selectedLeagueIndex = _selectedLeagueIndex.asStateFlow()

    val matchOveralls = selectedLeagueIndex
        .map { leagues.value[it] }
        .flatMapLatest {
            getMatchOverallsUseCase(GetMatchOverallsUseCase.Params())
                .filter { it is Result.Success }
                .map { (it as Result.Success).data }
                .mapLatest { it.insertSeparators() }
        }
        .cachedIn(viewModelScope)

    fun selectLeague(index: Int) {
        _selectedLeagueIndex.value = index
    }
}