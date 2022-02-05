package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.details.RankingDto
import io.github.cbinarycastle.betty.entity.Ranking

fun RankingDto.toEntity() = Ranking.Row(
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