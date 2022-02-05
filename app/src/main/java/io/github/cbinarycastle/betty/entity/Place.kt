package io.github.cbinarycastle.betty.entity

data class Place(
    val home: Value,
    val away: Value
) {
    data class Value(
        val totalMatchCount: Int,
        val winMatchCount: Int,
        val drawMatchCount: Int,
        val loseMatchCount: Int,
        val goalFor: Int,
        val goalAgainst: Int,
        val points: Int,
    )
}