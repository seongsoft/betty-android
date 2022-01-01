package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.*
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.RecommendType
import io.github.cbinarycastle.macao.entity.TeamInfo
import org.threeten.bp.LocalDateTime

val matchDetails = MatchDetails(
    id = "1",
    homeTeamInfo = manchesterUnited,
    awayTeamInfo = manchesterCity,
    recommendations = listOf(
        MatchRecommendation(
            description = "단지 추측입니다.",
            recommendType = RecommendType.HOME_WIN
        ),
        MatchRecommendation(
            description = "맨유가 좋습니다.",
            recommendType = RecommendType.HOME_WIN
        ),
        MatchRecommendation(
            description = "잘 모르겠습니다.",
            recommendType = RecommendType.DRAW
        ),
    ),
    relativeMatchHistories = listOf(
        MatchHistory(
            leagueName = "Premier League",
            matchedAt = LocalDateTime.of(2021, 12, 25, 6, 0, 0),
            homeTeamName = "Manchester United",
            homeTeamScore = 2,
            awayTeamName = "Manchester City",
            awayTeamScore = 1,
            handi = "",
            handiOutcome = "",
            isOdd = false,
            firstHalfScore = "1:1"
        ),
        MatchHistory(
            leagueName = "Premier League",
            matchedAt = LocalDateTime.of(2021, 12, 15, 21, 30, 0),
            homeTeamName = "Manchester City",
            homeTeamScore = 4,
            awayTeamName = "Manchester United",
            awayTeamScore = 2,
            handi = "",
            handiOutcome = "",
            isOdd = false,
            firstHalfScore = "2:2"
        )
    ),
    homeTeamMatchHistories = listOf(
        MatchHistory(
            leagueName = "Premier League",
            matchedAt = LocalDateTime.of(2021, 12, 24, 20, 0, 0),
            homeTeamName = "Manchester United",
            homeTeamScore = 1,
            awayTeamName = "Chelsea",
            awayTeamScore = 3,
            handi = "",
            handiOutcome = "",
            isOdd = false,
            firstHalfScore = "0:2"
        )
    ),
    awayTeamMatchHistories = listOf(
        MatchHistory(
            leagueName = "Premier League",
            matchedAt = LocalDateTime.of(2021, 12, 24, 15, 0, 0),
            homeTeamName = "Manchester City",
            homeTeamScore = 2,
            awayTeamName = "Liverpool",
            awayTeamScore = 2,
            handi = "",
            handiOutcome = "",
            isOdd = false,
            firstHalfScore = "1:1"
        )
    ),
    ranking = Ranking(
        type = Ranking.RankingType.LEAGUE,
        group = listOf(
            Ranking.RankingGroup(
                name = null,
                items = listOf(
                    Ranking.RankingInfo(
                        rankingNum = 1,
                        teamName = "Manchester City",
                        matches = 20,
                        win = 16,
                        draw = 2,
                        lose = 2,
                        score = 51,
                        losePoint = 12,
                        gamePoint = 50
                    ),
                    Ranking.RankingInfo(
                        rankingNum = 2,
                        teamName = "Chelsea",
                        matches = 20,
                        win = 12,
                        draw = 6,
                        lose = 2,
                        score = 43,
                        losePoint = 14,
                        gamePoint = 42
                    ),
                    Ranking.RankingInfo(
                        rankingNum = 3,
                        teamName = "Liverpool",
                        matches = 19,
                        win = 12,
                        draw = 5,
                        lose = 2,
                        score = 50,
                        losePoint = 16,
                        gamePoint = 41
                    )
                )
            )
        )
    )
)
