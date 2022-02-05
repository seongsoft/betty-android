package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.details.GoalPerMatchDto
import io.github.cbinarycastle.betty.entity.GoalsPerMatch

fun GoalPerMatchDto.toEntity() = GoalsPerMatch(
    number = number,
    teamName = teamName,
    teamImageUrl = teamImageUrl,
    matchCount = matchCount,
    gameCount0 = gameCount0,
    gameCount1 = gameCount1,
    gameCount2 = gameCount2,
    gameCount3 = gameCount3,
    gameCount4 = gameCount4,
    gameCount5 = gameCount5,
    gameCount6 = gameCount6,
    gameCountEtc = gameCountEtc,
    average = goalPerMatchAvg,
)