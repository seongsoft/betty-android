package io.github.cbinarycastle.betty.domain

import io.github.cbinarycastle.betty.entity.MatchDetails

interface MatchDetailsRepository {

    suspend fun getMatchDetails(matchId: Long): MatchDetails
}