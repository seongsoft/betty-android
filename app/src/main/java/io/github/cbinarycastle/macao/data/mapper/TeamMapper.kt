package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.TeamDto
import io.github.cbinarycastle.macao.entity.Outcome
import io.github.cbinarycastle.macao.entity.Team

fun TeamDto.toEntity() = Team(
    name = teamName,
    imageUrl = teamImageUrl,
    lastOutcomes = lastOutcomes.map { Outcome.valueOf(it) },
)