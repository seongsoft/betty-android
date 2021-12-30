package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.entity.MatchDetails

fun MatchDetailsResponse.toEntity() = MatchDetails(
    id = id,
    homeTeamInfo = homeTeamInfo.toEntity(),
    awayTeamInfo = awayTeamInfo.toEntity(),
    recommendation = recommendation.map { it.toEntity() },
    relativeMatchHistories = relativeMatchHistories.map { it.toEntity() },
    homeTeamMatchHistories = homeTeamMatchHistories.map { it.toEntity() },
    awayTeamMatchHistories = awayTeamMatchHistories.map { it.toEntity() },
    ranking = ranking.toEntity(),
)