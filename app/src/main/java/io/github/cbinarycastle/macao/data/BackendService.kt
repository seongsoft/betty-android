package io.github.cbinarycastle.macao.data

interface BackendService {

    suspend fun fetchMatchOveralls(): MatchOverallsResponse

    suspend fun fetchMatchDetails(): MatchDetailsResponse
}