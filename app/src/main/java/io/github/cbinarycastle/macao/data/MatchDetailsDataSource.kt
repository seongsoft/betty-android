package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.MatchDetails

interface MatchDetailsDataSource {

    suspend fun getMatchDetails(): MatchDetails
}