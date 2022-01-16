package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.entity.Ranking

fun GetMatchDetailsResponse.toEntity() = MatchDetails(
    matchAt = matchDateTime,
    league = league.toEntity(),
    homeTeam = homeTeam.toEntity(),
    awayTeam = awayTeam.toEntity(),
    suggestionInfo = suggestion.toEntity(),
    totalPlace = totalPlace.toEntity(),
    homePlace = homePlace.toEntity(),
    awayPlace = awayPlace.toEntity(),
    homeMatchHistories = homeMatchHistories.map { it.toEntity() },
    awayMatchHistories = awayMatchHistories.map { it.toEntity() },
    ranking = Ranking(rankings.map { it.toEntity() }),
    underOvers = underOverRankings.map { it.toEntity() },
    goalsPerMatches = goalPerMatches.map { it.toEntity() }
)