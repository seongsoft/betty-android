package io.github.cbinarycastle.macao.data.match.details

import io.github.cbinarycastle.macao.data.BackendService
import io.github.cbinarycastle.macao.data.mapper.toEntity
import io.github.cbinarycastle.macao.entity.MatchDetails
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