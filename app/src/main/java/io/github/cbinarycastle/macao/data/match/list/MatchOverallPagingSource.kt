package io.github.cbinarycastle.macao.data.match.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.cbinarycastle.macao.data.BackendService
import io.github.cbinarycastle.macao.data.mapper.toEntity
import io.github.cbinarycastle.macao.entity.MatchOverall
import org.threeten.bp.LocalDateTime

class MatchOverallPagingSource(
    private val backendService: BackendService,
    private val baseDateTime: LocalDateTime,
    private val leagueId: Long?,
) : PagingSource<Int, MatchOverall>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchOverall> {
        try {
            val page = params.key ?: 1
            val response = backendService.fetchMatches(
                baseDateTime = baseDateTime,
                leagueId = leagueId,
                page = page,
                size = params.loadSize,
            )

            return LoadResult.Page(
                data = response.content.map { it.toEntity() },
                prevKey = null,
                nextKey = if (response.page.hasNext) page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MatchOverall>): Int? = null
}