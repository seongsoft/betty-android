package io.github.cbinarycastle.macao.domain

data class TeamInfo(
    val teamName: String,
    val logoUrl: String,
    val recentRecords: List<OutCome>,
)