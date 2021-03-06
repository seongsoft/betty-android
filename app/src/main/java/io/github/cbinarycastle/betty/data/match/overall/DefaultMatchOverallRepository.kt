package io.github.cbinarycastle.betty.data.match.overall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.cbinarycastle.betty.domain.MatchOverallRepository
import io.github.cbinarycastle.betty.entity.MatchOverall
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchOverallRepository @Inject constructor(
    private val pagingSourceFactory: MatchOverallPagingSourceFactory
) : MatchOverallRepository {

    override fun getMatchOveralls(
        baseDateTime: LocalDateTime,
        leagueId: Long?,
        leagueName: String?,
        keyword: String?,
    ): Flow<PagingData<MatchOverall>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                pagingSourceFactory.create(
                    baseDateTime = baseDateTime,
                    leagueId = leagueId,
                    leagueName = leagueName,
                    keyword = keyword,
                )
            }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}