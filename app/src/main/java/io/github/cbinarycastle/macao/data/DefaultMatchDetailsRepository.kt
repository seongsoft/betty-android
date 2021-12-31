package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.domain.MatchDetailsRepository
import io.github.cbinarycastle.macao.entity.MatchDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchDetailsRepository @Inject constructor(
    private val dataSource: MatchDetailsDataSource
) : MatchDetailsRepository {

    override suspend fun getMatchDetails(): MatchDetails {
        return dataSource.getMatchDetails()
    }
}
