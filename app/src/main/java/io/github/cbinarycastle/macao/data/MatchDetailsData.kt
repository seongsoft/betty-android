package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.*
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.RecommendType
import io.github.cbinarycastle.macao.entity.TeamInfo
import org.threeten.bp.LocalDateTime

val matchDetails = MatchDetails(
    id = "1",
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
    ),
    recommendation = listOf(
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
            handi = ""
        )
    )
)
