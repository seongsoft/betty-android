package io.github.cbinarycastle.macao.domain

data class MatchRecommendation(
    val description: String,
    val recommendType: RecommendType
) {
    enum class RecommendType {
        HOME_WIN,
        DRAW,
        AWAY_WIN
    }
}