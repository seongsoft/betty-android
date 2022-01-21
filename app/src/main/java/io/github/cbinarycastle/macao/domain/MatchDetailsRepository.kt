package io.github.cbinarycastle.macao.domain

import io.github.cbinarycastle.macao.entity.MatchDetails

interface MatchDetailsRepository {

    suspend fun getMatchDetails(matchId: Long): MatchDetails
}