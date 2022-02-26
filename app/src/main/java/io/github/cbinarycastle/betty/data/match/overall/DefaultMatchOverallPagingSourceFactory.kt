package io.github.cbinarycastle.betty.data.match.overall

import androidx.paging.PagingSource
import io.github.cbinarycastle.betty.data.BackendService
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.event.EventLogger
import org.threeten.bp.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchOverallPagingSourceFactory @Inject constructor(
    private val backendService: BackendService,
    private val eventLogger: EventLogger,
) : MatchOverallPagingSourceFactory {

    override fun create(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
        leagueName: String?,
        keyword: String?,
    ): PagingSource<Int, MatchOverall> = MatchOverallPagingSource(
        backendService = backendService,
        eventLogger = eventLogger,
        baseDateTime = baseDateTime,
        leagueId = leagueId,
        leagueName = leagueName,
        keyword = keyword,
    )
}