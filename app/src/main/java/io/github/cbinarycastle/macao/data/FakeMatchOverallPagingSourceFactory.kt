package io.github.cbinarycastle.macao.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.cbinarycastle.macao.entity.MatchOverall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMatchOverallPagingSourceFactory @Inject constructor() : MatchOverallPagingSourceFactory {

    override fun create(): PagingSource<Int, MatchOverall> {
        return object : PagingSource<Int, MatchOverall>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchOverall> {
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
}