package io.github.cbinarycastle.betty.data.match.details

data class UnderOverDto(
    val number: Int,
    val teamName: String,
    val teamImageUrl: String,
    val matchCount: Int,
    val underCount: Int,
    val underPercent: Int,
    val overCount: Int,
    val overPercent: Int,
)