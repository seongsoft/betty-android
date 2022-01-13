package io.github.cbinarycastle.macao.data

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
    /**
     * 예측 정보 (HOME_WIN, DRAW, AWAY_WIN)
     * - Expected Percent 가 가장 높은 쪽으로 결정됨
     * - Expected Score 가 동점인 경우 DRAW 도 포함됨
     */
    val suggestions: List<String>
)