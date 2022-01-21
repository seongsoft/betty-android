package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.league.LeagueDto
import io.github.cbinarycastle.macao.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.macao.data.match.overall.GetMatchesResponse
import io.github.cbinarycastle.macao.entity.League
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.entity.MatchOverall

fun LeagueDto.toEntity() = League(
    id = id,
    name = name,
    imageUrl = imageUrl,
)

fun GetMatchesResponse.LeagueDto.toEntity() = MatchOverall.League(
    name = leagueName,
    imageUrl = leagueImageUrl,
)

fun GetMatchDetailsResponse.LeagueDto.toEntity() = MatchDetails.League(
    name = leagueName,
    imageUrl = leagueImageUrl,
)