package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.LeagueDto
import io.github.cbinarycastle.macao.entity.League

fun LeagueDto.toEntity() = League(
    name = leagueName,
    imageUrl = leagueImageUrl,
)