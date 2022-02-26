package io.github.cbinarycastle.betty.domain

import io.github.cbinarycastle.betty.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchTeamNamesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: SearchRepository,
) : FlowUseCase<SearchTeamNamesUseCase.Params, List<String>>(dispatcher) {

    override fun execute(params: Params): Flow<Result<List<String>>> = flow {
        val keyword = params.keyword
        if (keyword.isBlank()) {
            emit(Result.Success(emptyList()))
        } else {
            val teamNames = repository.searchTeamNames(keyword)
            emit(Result.Success(teamNames))
        }
    }

    data class Params(val keyword: String)
}