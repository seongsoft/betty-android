package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.data.match.SuggestionDto
import io.github.cbinarycastle.betty.entity.SuggestionInfo
import io.github.cbinarycastle.betty.entity.SuggestionType

fun SuggestionDto.toEntity() = SuggestionInfo(
    homeExpectedScore = homeExpectedScore,
    awayExpectedScore = awayExpectedScore,
    homeExpectedPercentage = homeExpectedPercent,
    drawExpectedPercentage = drawExpectedPercent,
    awayExpectedPercentage = awayExpectedPercent,
    suggestions = SuggestionType.valueOf(suggestions)
)