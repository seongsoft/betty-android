package io.github.cbinarycastle.betty.entity

data class UnderOver(
    val number: Int,
    val teamName: String,
    val teamImageUrl: String,
    val matchCount: Int,
    val underCount: Int,
    val underPercent: Int,
    val overCount: Int,
    val overPercent: Int,
)