package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.data.league.LeagueDto
import io.github.cbinarycastle.macao.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.macao.data.match.overall.GetMatchesResponse
import org.threeten.bp.LocalDateTime
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BackendService {

    @GET("/matches")
    suspend fun fetchMatches(
        @Query("baseDateTime") baseDateTime: LocalDateTime,
        @Query("leagueId") leagueId: Long?,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): GetMatchesResponse

    @GET("/matches/{matchId}")
    suspend fun fetchMatchDetails(@Path("matchId") matchId: Long): GetMatchDetailsResponse

    @GET("/leagues")
    suspend fun fetchLeagues(): List<LeagueDto>
}