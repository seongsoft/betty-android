package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.entity.MatchHistory

fun MatchDetailsResponse.MatchHistory.toEntity() = MatchHistory(
    leagueName = leagueName,
    matchedAt = matchedAt,
    homeTeamName = homeTeamName,
    homeTeamScore = homeTeamScore,
    awayTeamName = rightTeamName,
    awayTeamScore = awayTeamScore,
    handi = handi,
    handiOutcome = handiOutcome,
    isOdd = isOdd,
    firstHalfScore = firstHalfScore,
)