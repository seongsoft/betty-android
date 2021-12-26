package io.github.cbinarycastle.macao.domain

import io.github.cbinarycastle.macao.entity.MatchOverall

interface MatchOverallsRepository {

    suspend fun getMatchOveralls(): List<MatchOverall>
}