package io.github.cbinarycastle.macao.domain

import java.time.LocalDateTime

data class MatchOverall(
    val id: String,
    val leagueName: String,
    val matchAt: LocalDateTime,
    val recommend: OutCome,
    val homeTeamInfo: TeamInfo,
    val awayTeamInfo: TeamInfo,
)