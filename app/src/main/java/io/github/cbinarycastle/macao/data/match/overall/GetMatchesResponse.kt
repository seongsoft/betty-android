package io.github.cbinarycastle.macao.data.match.overall

import io.github.cbinarycastle.macao.data.PageDto
import io.github.cbinarycastle.macao.data.match.SuggestionDto

data class GetMatchesResponse(
    val content: List<MatchDto>,
    val page: PageDto,
) {
    data class MatchDto(
        val id: Long,
        /**
         * UTC Timezone
         */
        val matchDateTime: String,
        val league: LeagueDto,
        val homeTeam: TeamDto,
        val awayTeam: TeamDto,
        val suggestion: SuggestionDto,
    )

    data class LeagueDto(
        val leagueName: String,
        val leagueImageUrl: String,
    )

    data class TeamDto(
        val teamName: String,
        val teamImageUrl: String,
        val lastOutcomes: List<String>
    )
}