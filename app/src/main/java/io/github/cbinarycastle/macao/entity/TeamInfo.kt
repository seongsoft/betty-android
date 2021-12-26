package io.github.cbinarycastle.macao.entity

data class TeamInfo(
    val teamName: String,
    val logoUrl: String,
    val recentRecords: List<OutCome>,
)