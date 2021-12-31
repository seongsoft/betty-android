package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.MatchDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMatchDetailsDataSource @Inject constructor() : MatchDetailsDataSource {

    override suspend fun getMatchDetails(): MatchDetails {
        return matchDetails
    }
}