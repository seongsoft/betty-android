package io.github.cbinarycastle.betty.data.match.details

import io.github.cbinarycastle.betty.domain.MatchDetailsRepository
import io.github.cbinarycastle.betty.entity.MatchDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchDetailsRepository @Inject constructor(
    private val dataSource: MatchDetailsDataSource
) : MatchDetailsRepository {

    override suspend fun getMatchDetails(matchId: Long): MatchDetails {
        return dataSource.getMatchDetails(matchId)
    }
}
