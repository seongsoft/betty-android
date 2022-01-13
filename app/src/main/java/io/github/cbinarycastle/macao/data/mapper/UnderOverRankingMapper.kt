package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.UnderOverDto
import io.github.cbinarycastle.macao.entity.Ranking
import io.github.cbinarycastle.macao.entity.UnderOverRanking

fun UnderOverDto.toEntity() = UnderOverRanking.Row(
    number = number,
    teamName = teamName,
    matchCount = matchCount,
    winCount = winCount,
    drawCount = drawCount,
    loseCount = loseCount,
    goalFor = goalFor,
    goalAgainst = goalAgainst,
    points = points,
)