package io.github.cbinarycastle.macao.data.league

import io.github.cbinarycastle.macao.entity.League
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