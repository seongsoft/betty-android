package io.github.cbinarycastle.betty.data.match.details

data class RankingDto(
    val number: Int,
    val teamName: String,
    val teamImageUrl: String,
    val matchCount: Int,
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int,
    val goalFor: Int,
    val goalAgainst: Int,
    val points: Int
)