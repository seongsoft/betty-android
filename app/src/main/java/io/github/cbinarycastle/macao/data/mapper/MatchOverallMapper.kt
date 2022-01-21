package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.overall.GetMatchesResponse
import io.github.cbinarycastle.macao.entity.MatchOverall

fun GetMatchesResponse.MatchDto.toEntity() = MatchOverall(
    id = id,
    matchAt = matchDateTime,
    league = league.toEntity(),
    homeTeam = homeTeam.toEntity(),
    awayTeam = awayTeam.toEntity(),
    suggestionInfo = suggestion.toEntity(),
)