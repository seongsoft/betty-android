package io.github.cbinarycastle.macao.data.match.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.cbinarycastle.macao.entity.MatchOverall
import kotlinx.coroutines.delay
import org.threeten.bp.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMatchOverallPagingSourceFactory @Inject constructor() : MatchOverallPagingSourceFactory {

    override fun create(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
    ): PagingSource<Int, MatchOverall> = object : PagingSource<Int, MatchOverall>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchOverall> {
            delay(1000)
            return LoadResult.Page(
                data = matchOveralls,
                prevKey = null,
                nextKey = null
            )
        }

        override fun getRefreshKey(state: PagingState<Int, MatchOverall>): Int? {
            return null
        }
    }
}