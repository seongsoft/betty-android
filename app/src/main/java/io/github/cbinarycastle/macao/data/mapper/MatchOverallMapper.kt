package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.overall.GetMatchesResponse
import io.github.cbinarycastle.macao.entity.MatchOverall
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun GetMatchesResponse.MatchDto.toEntity() = MatchOverall(
    id = id,
    matchAt = LocalDateTime.parse(matchDateTime, DateTimeFormatter.ISO_DATE_TIME),
    league = league.toEntity(),
    homeTeam = homeTeam.toEntity(),
    awayTeam = awayTeam.toEntity(),
    suggestionInfo = suggestion.toEntity(),
)