package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.details.UnderOverDto
import io.github.cbinarycastle.betty.entity.UnderOver

fun UnderOverDto.toEntity() = UnderOver(
    number = number,
    teamName = teamName,
    matchCount = matchCount,
    underCount = underCount,
    underPercent = underPercent,
    overCount = overCount,
    overPercent = overPercent,
)