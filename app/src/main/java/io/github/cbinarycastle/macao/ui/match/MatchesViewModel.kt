package io.github.cbinarycastle.macao.ui.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.macao.domain.GetMatchOverallsUseCase
import io.github.cbinarycastle.macao.domain.Result
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatchOverallsUseCase: GetMatchOverallsUseCase,
) : ViewModel() {

    val matchOveralls = getMatchOverallsUseCase(Unit)
        .filter { it is Result.Success }
        .map { (it as Result.Success).data }
        .map { pagingData ->
            pagingData
                .map { matchOverall ->
                    MatchOverallModel.Item(matchOverall)
                }
                .insertSeparators { before, after ->
                    when {
                        after == null -> null
                        before == null -> MatchOverallModel.Separator(after.matchOverall.matchAt)
                        else -> {
                            if (
                                before.matchOverall.matchAt.toLocalDate() ==
                                after.matchOverall.matchAt.toLocalDate()
                            ) {
                                null
                            } else {
                                MatchOverallModel.Separator(after.matchOverall.matchAt)
                            }
                        }
                    }
                }
        }
        .cachedIn(viewModelScope)
}