package io.github.cbinarycastle.macao.domain

import io.github.cbinarycastle.macao.entity.League

interface LeagueRepository {

    suspend fun getLeagues(): List<League>
}