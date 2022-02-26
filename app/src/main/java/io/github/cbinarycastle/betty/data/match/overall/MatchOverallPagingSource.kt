package io.github.cbinarycastle.betty.data.match.overall

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.cbinarycastle.betty.data.BackendService
import io.github.cbinarycastle.betty.data.mapper.toEntity
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.event.Event
import io.github.cbinarycastle.betty.event.EventLogger
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

private const val INITIAL_PAGE = 0

class MatchOverallPagingSource(
    private val backendService: BackendService,
    private val eventLogger: EventLogger,
    private val baseDateTime: LocalDateTime,
    private val leagueId: Long?,
    private val leagueName: String?,
    private val keyword: String?,
) : PagingSource<Int, MatchOverall>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchOverall> {
        try {
            val page = params.key ?: INITIAL_PAGE
            val response = backendService.fetchMatches(
                baseDateTime = baseDateTime.format(DateTimeFormatter.ISO_DATE_TIME),
                leagueId = leagueId,
                keyword = keyword,
                page = page,
                size = params.loadSize,
            )

            return LoadResult.Page(
                data = response.content.map { it.toEntity() },
                prevKey = null,
                nextKey = if (response.page.hasNext) page + 1 else null
            )
        } catch (e: Exception) {
            Timber.e(e)
            eventLogger.logEvent(
                Event.MatchesMatchesLoadFailed(leagueName = leagueName)
            )
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MatchOverall>): Int? = null
}