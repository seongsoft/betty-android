package io.github.cbinarycastle.macao.ui.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.macao.domain.GetMatchOverallsUseCase
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.util.WhileViewSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MatchOverallsViewModel @Inject constructor(
    getMatchOverallsUseCase: GetMatchOverallsUseCase,
) : ViewModel() {

    val matchOveralls = flow {
        emit(getMatchOverallsUseCase(Unit))
    }.stateIn(viewModelScope, WhileViewSubscribed, Result.Loading)
}