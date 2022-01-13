package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.GetMatchDetailsResponse
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.entity.Ranking
import io.github.cbinarycastle.macao.entity.UnderOverRanking

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
    underOverRanking = UnderOverRanking(underOverRankings.map { it.toEntity() }),
    goalPerMatches = goalPerMatches.map { it.toEntity() }
)