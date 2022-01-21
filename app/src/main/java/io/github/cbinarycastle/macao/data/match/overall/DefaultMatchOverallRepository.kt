package io.github.cbinarycastle.macao.data.match.overall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.cbinarycastle.macao.domain.MatchOverallRepository
import io.github.cbinarycastle.macao.entity.MatchOverall
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
    ): Flow<PagingData<MatchOverall>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                pagingSourceFactory.create(
                    baseDateTime = baseDateTime,
                    leagueId = leagueId,
                )
            }
        ).flow
    }
}