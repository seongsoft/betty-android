package io.github.cbinarycastle.macao.data.match.list

import io.github.cbinarycastle.macao.data.match.LeagueDto
import io.github.cbinarycastle.macao.data.match.SuggestionDto
import io.github.cbinarycastle.macao.data.match.TeamDto
import org.threeten.bp.LocalDateTime

data class GetMatchesResponse(
    val data: List<MatchDto>,
) {
    data class MatchDto(
        val id: Long,
        /**
         * UTC Timezone
         */
        val matchDateTime: LocalDateTime,
        val leagueDto: LeagueDto,
        val homeTeamDto: TeamDto,
        val awayTeamDto: TeamDto,
        val suggestionDto: SuggestionDto,
    )
}