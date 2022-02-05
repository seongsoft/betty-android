package io.github.cbinarycastle.betty.data.league

import io.github.cbinarycastle.betty.data.BackendService
import io.github.cbinarycastle.betty.data.mapper.toEntity
import io.github.cbinarycastle.betty.entity.League
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteLeagueDataSource @Inject constructor(
    private val backendService: BackendService,
) : LeagueDataSource {

    override suspend fun getLeagues(): List<League> =
        backendService.fetchLeagues().map { it.toEntity() }
}