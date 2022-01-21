package io.github.cbinarycastle.macao.data.league

import io.github.cbinarycastle.macao.domain.LeagueRepository
import io.github.cbinarycastle.macao.entity.League
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLeagueRepository @Inject constructor(
    private val dataSource: LeagueDataSource,
) : LeagueRepository {

    override suspend fun getLeagues(): List<League> = dataSource.getLeagues()
}