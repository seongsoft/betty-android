package io.github.cbinarycastle.macao.data.match.overall

import androidx.paging.PagingSource
import io.github.cbinarycastle.macao.data.BackendService
import io.github.cbinarycastle.macao.entity.MatchOverall
import org.threeten.bp.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchOverallPagingSourceFactory @Inject constructor(
    private val backendService: BackendService,
) : MatchOverallPagingSourceFactory {

    override fun create(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
    ): PagingSource<Int, MatchOverall> = MatchOverallPagingSource(
        backendService = backendService,
        leagueId = leagueId,
        baseDateTime = baseDateTime,
    )
}