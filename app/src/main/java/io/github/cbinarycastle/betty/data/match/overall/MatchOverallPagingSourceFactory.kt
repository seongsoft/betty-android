package io.github.cbinarycastle.betty.data.match.overall

import androidx.paging.PagingSource
import io.github.cbinarycastle.betty.entity.MatchOverall
import org.threeten.bp.LocalDateTime

interface MatchOverallPagingSourceFactory {

    fun create(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
        leagueName: String?,
        keyword: String?,
    ): PagingSource<Int, MatchOverall>
}