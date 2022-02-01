package io.github.cbinarycastle.macao.di

import io.github.cbinarycastle.macao.data.match.details.MatchDetailsDataSource
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.entity.MatchDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMatchDetailsDataSource @Inject constructor() : MatchDetailsDataSource {

    override suspend fun getMatchDetails(matchId: Long): MatchDetails {
        return matchDetails
    }
}