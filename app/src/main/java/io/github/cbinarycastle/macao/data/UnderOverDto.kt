package io.github.cbinarycastle.macao.data

data class UnderOverDto(
    val number: Int,
    val teamName: String,
    val matchCount: Int,
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int,
    val goalFor: Int,
    val goalAgainst: Int,
    val points: Int
)