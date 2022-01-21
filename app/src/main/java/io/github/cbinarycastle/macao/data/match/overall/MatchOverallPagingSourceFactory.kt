package io.github.cbinarycastle.macao.data.match.overall

import androidx.paging.PagingSource
import io.github.cbinarycastle.macao.entity.MatchOverall
import org.threeten.bp.LocalDateTime

interface MatchOverallPagingSourceFactory {

    fun create(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
    ): PagingSource<Int, MatchOverall>
}