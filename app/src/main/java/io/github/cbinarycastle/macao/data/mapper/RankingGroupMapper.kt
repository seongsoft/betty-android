package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.entity.Ranking

fun MatchDetailsResponse.RankingGroup.toEntity() = Ranking.RankingGroup(
    name = name,
    items = items.map {
        Ranking.RankingInfo(
            rankingNum = it.rankingNum,
            teamName = it.teamName,
            matches = it.matches,
            win = it.win,
            draw = it.draw,
            lose = it.lose,
            score = it.score,
            losePoint = it.losePoint,
            gamePoint = it.gamePoint,
        )
    }
)
