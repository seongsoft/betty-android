package io.github.cbinarycastle.macao.entity

data class UnderOver(
    val number: Int,
    val teamName: String,
    val matchCount: Int,
    val underCount: Int,
    val underPercent: Int,
    val overCount: Int,
    val overPercent: Int,
)