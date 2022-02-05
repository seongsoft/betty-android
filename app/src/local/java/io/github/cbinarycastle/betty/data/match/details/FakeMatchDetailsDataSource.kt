package io.github.cbinarycastle.betty.data.match.details

import io.github.cbinarycastle.betty.entity.MatchDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMatchDetailsDataSource @Inject constructor() : MatchDetailsDataSource {

    override suspend fun getMatchDetails(matchId: Long): MatchDetails {
        return matchDetails
    }
}