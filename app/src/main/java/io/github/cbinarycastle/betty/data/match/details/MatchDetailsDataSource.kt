package io.github.cbinarycastle.betty.data.match.details

import io.github.cbinarycastle.betty.entity.MatchDetails

interface MatchDetailsDataSource {

    suspend fun getMatchDetails(matchId: Long): MatchDetails
}