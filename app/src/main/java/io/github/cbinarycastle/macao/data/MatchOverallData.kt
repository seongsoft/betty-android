package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.*
import org.threeten.bp.LocalDateTime

internal val premierLeague = League(
    name = "Premier League",
    imageUrl = "data:image/png;base64,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",
)

internal val manchesterUnited = Team(
    name = "Manchester United",
    imageUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/udQ6ns69PctCv143h-GeYw_96x96.png",
    lastOutcomes = listOf(
        Outcome.WIN,
        Outcome.WIN,
        Outcome.LOSE,
        Outcome.WIN,
        Outcome.DRAW,
    )
)

internal val manchesterCity = Team(
    name = "Manchester City",
    imageUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/z44l-a0W1v5FmgPnemV6Xw_96x96.png",
    lastOutcomes = listOf(
        Outcome.LOSE,
        Outcome.WIN,
        Outcome.DRAW,
        Outcome.WIN,
        Outcome.LOSE,
    )
)

val matchOveralls = listOf(
    MatchOverall(
        id = 1,
        league = premierLeague,
        matchAt = LocalDateTime.of(2021, 12, 25, 6, 0, 0),
        homeTeam = manchesterUnited,
        awayTeam = manchesterCity,
        suggestionInfo = SuggestionInfo(
            homeExpectedScore = 1,
            awayExpectedScore = 3,
            homeExpectedPercentage = 28,
            drawExpectedPercentage = 11,
            awayExpectedPercentage = 61,
            suggestions = listOf(SuggestionType.AWAY_WIN),
        )
    ),
    MatchOverall(
        id = 2,
        league = premierLeague,
        matchAt = LocalDateTime.of(2021, 12, 25, 9, 0, 0),
        homeTeam = Team(
            name = "Tottenham",
            imageUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/k3Q_mKE98Dnohrcea0JFgQ_96x96.png",
            lastOutcomes = listOf(
                Outcome.LOSE,
                Outcome.LOSE,
                Outcome.LOSE,
                Outcome.DRAW,
                Outcome.DRAW,
            )
        ),
        awayTeam = Team(
            name = "Arsenal",
            imageUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/4us2nCgl6kgZc0t3hpW75Q_96x96.png",
            lastOutcomes = listOf(
                Outcome.WIN,
                Outcome.WIN,
                Outcome.DRAW,
                Outcome.LOSE,
                Outcome.DRAW,
            )
        ),
        suggestionInfo = SuggestionInfo(
            homeExpectedScore = 1,
            awayExpectedScore = 0,
            homeExpectedPercentage = 50,
            drawExpectedPercentage = 18,
            awayExpectedPercentage = 32,
            suggestions = listOf(SuggestionType.HOME_WIN),
        )
    ),
    MatchOverall(
        id = 3,
        league = premierLeague,
        matchAt = LocalDateTime.of(2021, 12, 26, 20, 30, 0),
        homeTeam = Team(
            name = "Chelsea",
            imageUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/fhBITrIlbQxhVB6IjxUO6Q_96x96.png",
            lastOutcomes = listOf(
                Outcome.WIN,
                Outcome.DRAW,
                Outcome.LOSE,
                Outcome.WIN,
                Outcome.LOSE,
            )
        ),
        awayTeam = Team(
            name = "Liverpool",
            imageUrl = "https://ssl.gstatic.com/onebox/media/sports/logos/0iShHhASp5q1SL4JhtwJiw_96x96.png",
            lastOutcomes = listOf(
                Outcome.WIN,
                Outcome.WIN,
                Outcome.WIN,
                Outcome.WIN,
                Outcome.WIN,
            )
        ),
        suggestionInfo = SuggestionInfo(
            homeExpectedScore = 1,
            awayExpectedScore = 1,
            homeExpectedPercentage = 31,
            drawExpectedPercentage = 33,
            awayExpectedPercentage = 36,
            suggestions = listOf(SuggestionType.AWAY_WIN, SuggestionType.DRAW),
        )
    ),
)