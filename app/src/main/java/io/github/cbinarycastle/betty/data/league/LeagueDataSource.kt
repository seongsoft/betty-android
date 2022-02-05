package io.github.cbinarycastle.betty.data.league

import io.github.cbinarycastle.betty.entity.League

interface LeagueDataSource {

    suspend fun getLeagues(): List<League>
}