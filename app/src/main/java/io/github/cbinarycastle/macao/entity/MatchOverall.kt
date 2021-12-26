package io.github.cbinarycastle.macao.entity

import org.threeten.bp.LocalDateTime

data class MatchOverall(
    val id: String,
    val leagueName: String,
    val matchAt: LocalDateTime,
    val recommend: RecommendType,
    val homeTeamInfo: TeamInfo,
    val awayTeamInfo: TeamInfo,
)