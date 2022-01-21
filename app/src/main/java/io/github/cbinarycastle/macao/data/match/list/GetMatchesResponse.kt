package io.github.cbinarycastle.macao.data.match.list

import io.github.cbinarycastle.macao.data.PageDto
import io.github.cbinarycastle.macao.data.match.LeagueDto
import io.github.cbinarycastle.macao.data.match.SuggestionDto
import io.github.cbinarycastle.macao.data.match.TeamDto
import org.threeten.bp.LocalDateTime

data class GetMatchesResponse(
    val content: List<MatchDto>,
    val page: PageDto,
) {
    data class MatchDto(
        val id: Long,
        /**
         * UTC Timezone
         */
        val matchDateTime: LocalDateTime,
        val league: LeagueDto,
        val homeTeam: TeamDto,
        val awayTeam: TeamDto,
        val suggestion: SuggestionDto,
    )
}