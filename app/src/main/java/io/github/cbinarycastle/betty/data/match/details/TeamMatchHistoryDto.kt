package io.github.cbinarycastle.betty.data.match.details

data class TeamMatchHistoryDto(
    /**
     * YYYY-MM-dd
     */
    val date: String,
    val homeTeamName: String,
    val awayTeamName: String,

    /**
     * WIN, DRAW, LOSE
     */
    val outcome: String,
    val homeScore: Int,
    val awayScore: Int,
)