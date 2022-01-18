package io.github.cbinarycastle.macao.data.match.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.cbinarycastle.macao.domain.MatchOverallRepository
import io.github.cbinarycastle.macao.entity.MatchOverall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchOverallRepository @Inject constructor(
    private val pagingSourceFactory: MatchOverallPagingSourceFactory
) : MatchOverallRepository {

    override fun getMatchOveralls(): Flow<PagingData<MatchOverall>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { pagingSourceFactory.create() }
        ).flow
    }
}