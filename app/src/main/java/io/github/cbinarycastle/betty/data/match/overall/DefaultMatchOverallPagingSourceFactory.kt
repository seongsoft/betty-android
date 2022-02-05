package io.github.cbinarycastle.betty.data.match.overall

import androidx.paging.PagingSource
import io.github.cbinarycastle.betty.data.BackendService
import io.github.cbinarycastle.betty.entity.MatchOverall
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