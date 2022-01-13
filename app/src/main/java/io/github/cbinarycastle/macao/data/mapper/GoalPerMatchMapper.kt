package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.GoalPerMatchDto
import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.data.RankingDto
import io.github.cbinarycastle.macao.entity.GoalPerMatch
import io.github.cbinarycastle.macao.entity.Ranking

fun GoalPerMatchDto.toEntity() = GoalPerMatch(
    number = number,
    teamName = teamName,
    matchCount = matchCount,
    gameCount0 = gameCount0,
    gameCount1 = gameCount1,
    gameCount2 = gameCount2,
    gameCount3 = gameCount3,
    gameCount4 = gameCount4,
    gameCount5 = gameCount5,
    gameCount6 = gameCount6,
    gameCountEtc = gameCountEtc,
    goalPerMatchAvg = goalPerMatchAvg,
)