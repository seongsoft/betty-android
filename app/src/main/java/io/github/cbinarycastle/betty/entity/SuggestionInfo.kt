package io.github.cbinarycastle.betty.entity

data class SuggestionInfo(
    val homeExpectedScore: Int,
    val awayExpectedScore: Int,
    val homeExpectedPercentage: Int,
    val drawExpectedPercentage: Int,
    val awayExpectedPercentage: Int,
    val suggestions: SuggestionType,
)