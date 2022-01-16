package io.github.cbinarycastle.macao.entity

import org.threeten.bp.LocalDateTime

data class MatchDetails(
    val matchAt: LocalDateTime,
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
)