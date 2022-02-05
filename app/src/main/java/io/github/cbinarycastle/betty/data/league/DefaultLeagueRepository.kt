package io.github.cbinarycastle.betty.data.league

import io.github.cbinarycastle.betty.domain.LeagueRepository
import io.github.cbinarycastle.betty.entity.League
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLeagueRepository @Inject constructor(
    private val dataSource: LeagueDataSource,
) : LeagueRepository {

    override suspend fun getLeagues(): List<League> = dataSource.getLeagues()
}