package io.github.cbinarycastle.betty.data.match.details

import io.github.cbinarycastle.betty.data.BackendService
import io.github.cbinarycastle.betty.data.mapper.toEntity
import io.github.cbinarycastle.betty.entity.MatchDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteMatchDetailsSource @Inject constructor(
    private val backendService: BackendService
) : MatchDetailsDataSource {

    override suspend fun getMatchDetails(matchId: Long): MatchDetails {
        return backendService.fetchMatchDetails(matchId).toEntity()
    }
}