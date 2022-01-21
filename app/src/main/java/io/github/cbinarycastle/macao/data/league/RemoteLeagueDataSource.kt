package io.github.cbinarycastle.macao.data.league

import io.github.cbinarycastle.macao.data.BackendService
import io.github.cbinarycastle.macao.data.mapper.toEntity
import io.github.cbinarycastle.macao.entity.League
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteLeagueDataSource @Inject constructor(
    private val backendService: BackendService,
) : LeagueDataSource {

    override suspend fun getLeagues(): List<League> =
        backendService.fetchLeagues().map { it.toEntity() }
}