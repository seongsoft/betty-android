package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.TeamInfo

fun TeamInfo.toEntity() = io.github.cbinarycastle.macao.entity.Team(
    name = teamName,
    imageUrl = logoUrl,
    lastOutcomes = recentRecords.map { it.toEntity() },
)