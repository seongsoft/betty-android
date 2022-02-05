package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.details.TeamMatchHistoryDto
import io.github.cbinarycastle.betty.entity.MatchHistory
import io.github.cbinarycastle.betty.entity.Outcome
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