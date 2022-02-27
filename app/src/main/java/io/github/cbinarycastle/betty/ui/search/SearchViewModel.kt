package io.github.cbinarycastle.betty.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.betty.domain.SearchTeamNamesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchTeamNamesUseCase: SearchTeamNamesUseCase,
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalTime::class)
    val matchedTeams = keyword
        .flatMapLatest {
            delay(DEBOUNCE_MILLIS)
            searchTeamNamesUseCase(SearchTeamNamesUseCase.Params(it))
        }

    fun updateKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun clearKeyword() {
        _keyword.value = ""
    }

    companion object {
        private const val DEBOUNCE_MILLIS = 200L
    }
}