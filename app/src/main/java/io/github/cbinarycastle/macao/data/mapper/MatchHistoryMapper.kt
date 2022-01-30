package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.details.TeamMatchHistoryDto
import io.github.cbinarycastle.macao.entity.MatchHistory
import io.github.cbinarycastle.macao.entity.Outcome
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun TeamMatchHistoryDto.toEntity() = MatchHistory(
    date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
    homeTeamName = homeTeamName,
    awayTeamName = awayTeamName,
    outcome = Outcome.valueOf(outcome),
    homeScore = homeScore,
    awayScore = awayScore,
)