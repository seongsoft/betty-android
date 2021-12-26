package io.github.cbinarycastle.macao.data

import org.threeten.bp.LocalDateTime

data class MatchOverallsResponse(
    val id: String,
    val leagueName: String,
    val matchAt: LocalDateTime,
    val recommend: RecommendType,
    val homeTeamInfo: TeamInfo,
    val awayTeamInfo: TeamInfo,
)