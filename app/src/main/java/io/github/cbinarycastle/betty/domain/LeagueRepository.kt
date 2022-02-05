package io.github.cbinarycastle.betty.domain

import io.github.cbinarycastle.betty.entity.League

interface LeagueRepository {

    suspend fun getLeagues(): List<League>
}