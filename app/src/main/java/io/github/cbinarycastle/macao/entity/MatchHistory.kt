package io.github.cbinarycastle.macao.entity

import org.threeten.bp.LocalDate

data class MatchHistory(
    val date: LocalDate,
    val homeTeamName: String,
    val awayTeamName: String,
    val outcome: Outcome,
    val homeScore: Int,
    val awayScore: Int,
)