package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.RecommendType
import io.github.cbinarycastle.macao.entity.TeamInfo
import org.threeten.bp.LocalDateTime

internal val manchesterUnited = TeamInfo(
    teamName = "Manchester United",
    logoUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/udQ6ns69PctCv143h-GeYw_96x96.png",
    recentRecords = listOf(
        OutCome.WIN,
        OutCome.WIN,
        OutCome.LOSE,
        OutCome.WIN,
        OutCome.DRAW,
    )
)

internal val manchesterCity = TeamInfo(
    teamName = "Manchester City",
    logoUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/z44l-a0W1v5FmgPnemV6Xw_96x96.png",
    recentRecords = listOf(
        OutCome.LOSE,
        OutCome.WIN,
        OutCome.DRAW,
        OutCome.WIN,
        OutCome.LOSE,
    )
)

val matchOveralls = listOf(
    MatchOverall(
        id = "1",
        leagueName = "Premier League",
        matchAt = LocalDateTime.of(2021, 12, 25, 6, 0, 0),
        recommend = RecommendType.HOME_WIN,
        homeTeamInfo = manchesterUnited,
        awayTeamInfo = manchesterCity,
    ),
    MatchOverall(
        id = "2",
        leagueName = "Premier League",
        matchAt = LocalDateTime.of(2021, 12, 25, 9, 0, 0),
        recommend = RecommendType.AWAY_WIN,
        homeTeamInfo = TeamInfo(
            teamName = "Tottenham",
            logoUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/k3Q_mKE98Dnohrcea0JFgQ_96x96.png",
            recentRecords = listOf(
                OutCome.LOSE,
                OutCome.LOSE,
                OutCome.LOSE,
                OutCome.DRAW,
                OutCome.DRAW,
            )
        ),
        awayTeamInfo = TeamInfo(
            teamName = "Arsenal",
            logoUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/4us2nCgl6kgZc0t3hpW75Q_96x96.png",
            recentRecords = listOf(
                OutCome.WIN,
                OutCome.WIN,
                OutCome.DRAW,
                OutCome.LOSE,
                OutCome.DRAW,
            )
        )
    ),
    MatchOverall(
        id = "3",
        leagueName = "Premier League",
        matchAt = LocalDateTime.of(2021, 12, 24, 20, 30, 0),
        recommend = RecommendType.DRAW,
        homeTeamInfo = TeamInfo(
            teamName = "Tottenham",
            logoUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/fhBITrIlbQxhVB6IjxUO6Q_96x96.png",
            recentRecords = listOf(
                OutCome.WIN,
                OutCome.DRAW,
                OutCome.LOSE,
                OutCome.WIN,
                OutCome.LOSE,
            )
        ),
        awayTeamInfo = TeamInfo(
            teamName = "Liverpool",
            logoUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/0iShHhASp5q1SL4JhtwJiw_96x96.png",
            recentRecords = listOf(
                OutCome.WIN,
                OutCome.WIN,
                OutCome.WIN,
                OutCome.WIN,
                OutCome.WIN,
            )
        )
    ),
)