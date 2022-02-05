package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.betty.entity.MatchDetails
import io.github.cbinarycastle.betty.entity.Ranking
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

fun GetMatchDetailsResponse.toEntity() = MatchDetails(
    matchAt = LocalDateTime
        .parse(matchDateTime, DateTimeFormatter.ISO_DATE_TIME)
        .atZone(ZoneOffset.UTC),
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