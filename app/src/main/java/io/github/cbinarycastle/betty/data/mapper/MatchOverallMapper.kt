package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.overall.GetMatchesResponse
import io.github.cbinarycastle.betty.entity.MatchOverall
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

fun GetMatchesResponse.MatchDto.toEntity() = MatchOverall(
    id = id,
    matchAt = LocalDateTime
        .parse(matchDateTime, DateTimeFormatter.ISO_DATE_TIME)
        .atZone(ZoneOffset.UTC),
    league = league.toEntity(),
    homeTeam = homeTeam.toEntity(),
    awayTeam = awayTeam.toEntity(),
    suggestionInfo = suggestion.toEntity(),
)