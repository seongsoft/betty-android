package io.github.cbinarycastle.macao.ui.match.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.macao.domain.GetMatchDetailsUseCase
import io.github.cbinarycastle.macao.domain.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    getMatchDetailsUseCase: GetMatchDetailsUseCase,
) : ViewModel() {

    private val matchId = MutableStateFlow<Long?>(null)

    val matchDetails = matchId
        .filterNotNull()
        .mapLatest { getMatchDetailsUseCase(GetMatchDetailsUseCase.Params(it)) }

    fun setMatchId(matchId: Long) {
        this.matchId.value = matchId
    }
}