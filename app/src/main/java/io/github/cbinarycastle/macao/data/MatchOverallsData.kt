package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.RecommendType
import io.github.cbinarycastle.macao.entity.TeamInfo
import org.threeten.bp.LocalDateTime

val matchOveralls = listOf(
    MatchOverall(
        id = "1",
        leagueName = "Premier League",
        matchAt = LocalDateTime.of(2021, 12, 25, 6, 0, 0),
        recommend = RecommendType.HOME_WIN,
        homeTeamInfo = TeamInfo(
            teamName = "Manchester United",
            logoUrl = "url",
            recentRecords = listOf(
                OutCome.WIN,
                OutCome.WIN,
                OutCome.LOSE,
                OutCome.WIN,
                OutCome.DRAW,
            )
        ),
        awayTeamInfo = TeamInfo(
            teamName = "Manchester City",
            logoUrl = "url",
            recentRecords = listOf(
                OutCome.LOSE,
                OutCome.WIN,
                OutCome.DRAW,
                OutCome.WIN,
                OutCome.LOSE,
            )
        )
    ),
    MatchOverall(
        id = "2",
        leagueName = "Premier League",
        matchAt = LocalDateTime.of(2021, 12, 25, 9, 0, 0),
        recommend = RecommendType.AWAY_WIN,
        homeTeamInfo = TeamInfo(
            teamName = "Wolverhampton",
            logoUrl = "url",
            recentRecords = listOf(
                OutCome.LOSE,
                OutCome.LOSE,
                OutCome.LOSE,
                OutCome.DRAW,
                OutCome.DRAW,
            )
        ),
        awayTeamInfo = TeamInfo(
            teamName = "Sheffield United",
            logoUrl = "url",
            recentRecords = listOf(
                OutCome.WIN,
                OutCome.WIN,
                OutCome.DRAW,
                OutCome.LOSE,
                OutCome.DRAW,
            )
        )
    ),
)