package io.github.cbinarycastle.betty.domain

import androidx.paging.PagingData
import io.github.cbinarycastle.betty.di.IoDispatcher
import io.github.cbinarycastle.betty.entity.MatchOverall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import javax.inject.Inject

class GetMatchOverallsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MatchOverallRepository,
) : FlowUseCase<GetMatchOverallsUseCase.Params, PagingData<MatchOverall>>(dispatcher) {

    override fun execute(params: Params): Flow<Result<PagingData<MatchOverall>>> {
        return repository.getMatchOveralls(
            baseDateTime = params.baseDateTime,
            leagueId = params.leagueId,
            leagueName = params.leagueName,
            keyword = params.keyword,
        ).map { Result.Success(it) }
    }

    data class Params(
        val leagueId: Long? = null,
        val leagueName: String? = null,
        val keyword: String? = null,
        val baseDateTime: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC).minusHours(2),
    )
}