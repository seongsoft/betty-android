package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.SuggestionDto
import io.github.cbinarycastle.macao.entity.SuggestionInfo
import io.github.cbinarycastle.macao.entity.SuggestionType

fun SuggestionDto.toEntity() = SuggestionInfo(
    homeExpectedScore = homeExpectedScore,
    awayExpectedScore = awayExpectedScore,
    homeExpectedPercentage = homeExpectedPercent,
    drawExpectedPercentage = drawExpectedPercent,
    awayExpectedPercentage = awayExpectedPercent,
    suggestions = suggestions.map { SuggestionType.valueOf(it) }
)