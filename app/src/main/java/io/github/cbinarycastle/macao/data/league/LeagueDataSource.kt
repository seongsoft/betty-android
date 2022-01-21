package io.github.cbinarycastle.macao.data.league

import io.github.cbinarycastle.macao.entity.League

interface LeagueDataSource {

    suspend fun getLeagues(): List<League>
}