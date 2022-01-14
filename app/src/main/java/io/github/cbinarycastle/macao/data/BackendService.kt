package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.macao.data.match.list.GetMatchesResponse

interface BackendService {

    suspend fun fetchMatches(): GetMatchesResponse

    suspend fun fetchMatchDetails(): GetMatchDetailsResponse
}