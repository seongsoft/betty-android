package io.github.cbinarycastle.macao.domain

data class RankingInfo(
    val rankingNum: Int,
    val teamName: String,
    val matches: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    val score: Int,
    val losePoint: Int,
    val gamePoint: Int,
)