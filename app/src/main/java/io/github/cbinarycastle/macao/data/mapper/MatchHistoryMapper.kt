package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.details.TeamMatchHistoryDto
import io.github.cbinarycastle.macao.entity.MatchHistory
import io.github.cbinarycastle.macao.entity.Outcome

fun TeamMatchHistoryDto.toEntity() = MatchHistory(
    date = date,
    homeTeamName = homeTeamName,
    awayTeamName = awayTeamName,
    outcome = Outcome.valueOf(outcome),
    homeScore = homeScore,
    awayScore = awayScore,
)