package io.github.cbinarycastle.macao.domain

import androidx.paging.PagingData
import io.github.cbinarycastle.macao.entity.MatchOverall
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDateTime

interface MatchOverallRepository {

    fun getMatchOveralls(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
    ): Flow<PagingData<MatchOverall>>
}