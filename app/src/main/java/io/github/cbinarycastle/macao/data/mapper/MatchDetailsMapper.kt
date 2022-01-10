package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.data.RecommendType
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.entity.MatchRecommendation
import io.github.cbinarycastle.macao.entity.SuggestionInfo

fun MatchDetailsResponse.toEntity() = MatchDetails(
    id = id,
    homeTeam = homeTeamInfo.toEntity(),
    awayTeam = awayTeamInfo.toEntity(),
    relativeMatchHistories = relativeMatchHistories.map { it.toEntity() },
    homeTeamMatchHistories = homeTeamMatchHistories.map { it.toEntity() },
    awayTeamMatchHistories = awayTeamMatchHistories.map { it.toEntity() },
    ranking = ranking.toEntity(),
)