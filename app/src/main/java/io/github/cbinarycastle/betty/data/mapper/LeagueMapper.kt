package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.league.LeagueDto
import io.github.cbinarycastle.betty.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.betty.data.match.overall.GetMatchesResponse
import io.github.cbinarycastle.betty.entity.League
import io.github.cbinarycastle.betty.entity.MatchDetails
import io.github.cbinarycastle.betty.entity.MatchOverall

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