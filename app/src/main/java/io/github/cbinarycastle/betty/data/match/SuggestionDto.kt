package io.github.cbinarycastle.betty.data.match

data class SuggestionDto(
    /**
     * 예측 스코어 정보
     */
    val homeExpectedScore: Int,
    val awayExpectedScore: Int,
    /**
     * 예측 % 정보
     */
    val homeExpectedPercent: Int,
    val drawExpectedPercent: Int,
    val awayExpectedPercent: Int,

    val suggestions: String
)