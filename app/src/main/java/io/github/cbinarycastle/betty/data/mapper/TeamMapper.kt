package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.details.GetMatchDetailsResponse
import io.github.cbinarycastle.betty.data.match.overall.GetMatchesResponse
import io.github.cbinarycastle.betty.entity.Outcome
import io.github.cbinarycastle.betty.entity.Team

fun GetMatchesResponse.TeamDto.toEntity() = Team(
    originalName = teamName,
    displayName = teamName,
    imageUrl = teamImageUrl,
    lastOutcomes = lastOutcomes.map { Outcome.valueOf(it) },
)

fun GetMatchDetailsResponse.TeamDto.toEntity() = Team(
    originalName = teamKey,
    displayName = teamName,
    imageUrl = teamImageUrl,
    lastOutcomes = lastOutcomes.map { Outcome.valueOf(it) },
)