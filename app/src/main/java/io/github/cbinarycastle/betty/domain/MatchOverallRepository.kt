package io.github.cbinarycastle.betty.domain

import androidx.paging.PagingData
import io.github.cbinarycastle.betty.entity.MatchOverall
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDateTime

interface MatchOverallRepository {

    fun getMatchOveralls(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
        leagueName: String?,
        keyword: String?,
    ): Flow<PagingData<MatchOverall>>
}