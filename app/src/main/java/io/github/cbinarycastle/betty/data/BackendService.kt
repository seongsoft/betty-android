package io.github.cbinarycastle.betty.data

import io.github.cbinarycastle.betty.data.league.LeagueDto
import io.github.cbinarycastle.betty.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.betty.data.match.overall.GetMatchesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BackendService {

    @GET("matches")
    suspend fun fetchMatches(
        @Query("baseDateTime") baseDateTime: String,
        @Query("leagueId") leagueId: Long?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): GetMatchesResponse

    @GET("matches/{matchId}")
    suspend fun fetchMatchDetails(@Path("matchId") matchId: Long): GetMatchDetailsResponse

    @GET("leagues")
    suspend fun fetchLeagues(): List<LeagueDto>
}