package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

val matchDetails = MatchDetails(
    matchAt = LocalDateTime.of(2021, 12, 25, 6, 0, 0),
    league = premierLeague,
    homeTeam = manchesterUnited,
    awayTeam = manchesterCity,
    homeMatchHistories = listOf(
        MatchHistory(
            homeTeamName = "Manchester United",
            awayTeamName = "Manchester City",
            date = LocalDate.of(2021, 12, 25),
            outcome = Outcome.WIN,
            homeScore = 2,
            awayScore = 1,
        ),
        MatchHistory(
            date = LocalDate.of(2021, 12, 24),
            homeTeamName = "Manchester United",
            awayTeamName = "Chelsea",
            outcome = Outcome.LOSE,
            homeScore = 1,
            awayScore = 3,
        ),
        MatchHistory(
            date = LocalDate.of(2021, 12, 15),
            homeTeamName = "Manchester City",
            awayTeamName = "Manchester United",
            outcome = Outcome.LOSE,
            homeScore = 4,
            awayScore = 2,
        ),
    ),
    awayMatchHistories = listOf(
        MatchHistory(
            date = LocalDate.of(2021, 12, 24),
            homeTeamName = "Manchester City",
            awayTeamName = "Liverpool",
            outcome = Outcome.DRAW,
            homeScore = 2,
            awayScore = 2,
        ),
    ),
    ranking = Ranking(
        rows = listOf(
            Ranking.Row(
                number = 1,
                teamName = "Manchester City",
                matchCount = 21,
                winCount = 17,
                drawCount = 2,
                loseCount = 2,
                goalFor = 53,
                goalAgainst = 13,
                points = 53
            ),
            Ranking.Row(
                number = 2,
                teamName = "Chelsea",
                matchCount = 21,
                winCount = 12,
                drawCount = 7,
                loseCount = 2,
                goalFor = 45,
                goalAgainst = 16,
                points = 43
            ),
            Ranking.Row(
                number = 3,
                teamName = "Liverpool",
                matchCount = 20,
                winCount = 12,
                drawCount = 6,
                loseCount = 2,
                goalFor = 52,
                goalAgainst = 18,
                points = 42
            ),
        )
    ),
    suggestionInfo = SuggestionInfo(
        homeExpectedScore = 1,
        awayExpectedScore = 3,
        homeExpectedPercentage = 21,
        drawExpectedPercentage = 24,
        awayExpectedPercentage = 55,
        suggestions = listOf(SuggestionType.AWAY_WIN)
    ),
    totalPlace = Place(
        home = Place.Value(
            totalMatchCount = 14,
            winMatchCount = 10,
            drawMatchCount = 1,
            loseMatchCount = 3,
            goalFor = 17,
            goalAgainst = 5,
            points = 21
        ),
        away = Place.Value(
            totalMatchCount = 15,
            winMatchCount = 6,
            drawMatchCount = 4,
            loseMatchCount = 5,
            goalFor = 9,
            goalAgainst = 12,
            points = 13
        )
    ),
    homePlace = Place(
        home = Place.Value(
            totalMatchCount = 14,
            winMatchCount = 10,
            drawMatchCount = 1,
            loseMatchCount = 3,
            goalFor = 17,
            goalAgainst = 5,
            points = 21
        ),
        away = Place.Value(
            totalMatchCount = 15,
            winMatchCount = 6,
            drawMatchCount = 4,
            loseMatchCount = 5,
            goalFor = 9,
            goalAgainst = 12,
            points = 13
        )
    ),
    awayPlace = Place(
        home = Place.Value(
            totalMatchCount = 14,
            winMatchCount = 10,
            drawMatchCount = 1,
            loseMatchCount = 3,
            goalFor = 17,
            goalAgainst = 5,
            points = 21
        ),
        away = Place.Value(
            totalMatchCount = 15,
            winMatchCount = 6,
            drawMatchCount = 4,
            loseMatchCount = 5,
            goalFor = 9,
            goalAgainst = 12,
            points = 13
        )
    ),
    underOverRanking = UnderOverRanking(
        rows = listOf(
            UnderOverRanking.Row(
                number = 1,
                teamName = "Manchester City",
                matchCount = 21,
                winCount = 17,
                drawCount = 2,
                loseCount = 2,
                goalFor = 53,
                goalAgainst = 13,
                points = 53
            ),
            UnderOverRanking.Row(
                number = 2,
                teamName = "Chelsea",
                matchCount = 21,
                winCount = 12,
                drawCount = 7,
                loseCount = 2,
                goalFor = 45,
                goalAgainst = 16,
                points = 43
            ),
            UnderOverRanking.Row(
                number = 3,
                teamName = "Liverpool",
                matchCount = 20,
                winCount = 12,
                drawCount = 6,
                loseCount = 2,
                goalFor = 52,
                goalAgainst = 18,
                points = 42
            ),
        )
    ),
    goalPerMatches = listOf(
        GoalPerMatch(
            number = 1,
            teamName = "Manchester City",
            matchCount = 21,
            gameCount0 = 2,
            gameCount1 = 3,
            gameCount2 = 4,
            gameCount3 = 5,
            gameCount4 = 6,
            gameCount5 = 7,
            gameCount6 = 8,
            gameCountEtc = 0,
            goalPerMatchAvg = 3.5
        )
    )
)
