package io.github.cbinarycastle.betty.data

import io.github.cbinarycastle.betty.data.league.LeagueDataSource
import io.github.cbinarycastle.betty.data.league.leagues
import io.github.cbinarycastle.betty.entity.League
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeLeagueDataSource @Inject constructor() : LeagueDataSource {

    override suspend fun getLeagues(): List<League> {
        delay(500)
        return leagues
    }
}