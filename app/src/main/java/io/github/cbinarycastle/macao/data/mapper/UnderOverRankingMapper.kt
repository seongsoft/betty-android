package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.match.details.UnderOverDto
import io.github.cbinarycastle.macao.entity.UnderOver

fun UnderOverDto.toEntity() = UnderOver(
    number = number,
    teamName = teamName,
    matchCount = matchCount,
    underCount = underCount,
    underPercent = underPercent,
    overCount = overCount,
    overPercent = overPercent,
)