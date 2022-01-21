package io.github.cbinarycastle.macao.data.match.details

import io.github.cbinarycastle.macao.PlaceDto
import io.github.cbinarycastle.macao.data.match.*
import org.threeten.bp.LocalDateTime

data class GetMatchDetailsResponse(
    /**
     * UTC Timezone
     */
    val matchDateTime: LocalDateTime,

    val league: LeagueDto,

    val homeTeam: TeamDto,

    val awayTeam: TeamDto,

    val suggestion: SuggestionDto,

    /**
     * HOME/AWAY Total, Home, Away 순위 요약 정보
     */
    val totalPlace: PlaceDto,
    val homePlace: PlaceDto,
    val awayPlace: PlaceDto,

    val homeMatchHistories: List<TeamMatchHistoryDto>,
    val awayMatchHistories: List<TeamMatchHistoryDto>,

    val rankings : List<RankingDto>,
    val underOverRankings: List<UnderOverDto>,
    val goalPerMatches: List<GoalPerMatchDto>
) {
    data class LeagueDto(
        val leagueName: String,
        val leagueImageUrl: String,
    )
}