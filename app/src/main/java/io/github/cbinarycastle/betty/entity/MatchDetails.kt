package io.github.cbinarycastle.betty.entity

import org.threeten.bp.ZonedDateTime

data class MatchDetails(
    val matchAt: ZonedDateTime,
    val league: League,
    val homeTeam: Team,
    val awayTeam: Team,
    val suggestionInfo: SuggestionInfo,
    val totalPlace: Place,
    val homePlace: Place,
    val awayPlace: Place,
    val homeMatchHistories: List<MatchHistory>,
    val awayMatchHistories: List<MatchHistory>,
    val ranking: Ranking,
    val underOvers: List<UnderOver>,
    val goalsPerMatches: List<GoalsPerMatch>
) {
    data class League(
        val name: String,
        val imageUrl: String,
    )
}