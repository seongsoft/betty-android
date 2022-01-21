package io.github.cbinarycastle.macao.data.match.details

import io.github.cbinarycastle.macao.entity.MatchDetails

interface MatchDetailsDataSource {

    suspend fun getMatchDetails(matchId: Long): MatchDetails
}