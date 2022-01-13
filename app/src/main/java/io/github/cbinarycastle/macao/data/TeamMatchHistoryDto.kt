package io.github.cbinarycastle.macao.data

import org.threeten.bp.LocalDate

data class TeamMatchHistoryDto(
    /**
     * YYYY-MM-dd
     */
    val date: LocalDate,
    val homeTeamName: String,
    val awayTeamName: String,

    /**
     * WIN, DRAW, LOSE
     */
    val outcome: String,
    val homeScore: Int,
    val awayScore: Int,
)