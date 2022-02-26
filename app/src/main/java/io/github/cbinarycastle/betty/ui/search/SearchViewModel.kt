package io.github.cbinarycastle.betty.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.betty.domain.SearchTeamNamesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchTeamNamesUseCase: SearchTeamNamesUseCase,
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalTime::class)
    val matchedTeams = keyword
        .flatMapLatest { searchTeamNamesUseCase(SearchTeamNamesUseCase.Params(it)) }
        .debounce(1000.milliseconds)

    fun updateKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun clearKeyword() {
        _keyword.value = ""
    }
}