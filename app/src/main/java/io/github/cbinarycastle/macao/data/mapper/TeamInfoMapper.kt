package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.TeamInfo

fun TeamInfo.toEntity() = io.github.cbinarycastle.macao.entity.TeamInfo(
    teamName = teamName,
    logoUrl = logoUrl,
    recentRecords = recentRecords.map { it.toEntity() },
)