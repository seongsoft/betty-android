package io.github.cbinarycastle.macao.data

data class TeamInfo(
    val teamName: String,
    val logoUrl: String,
    val recentRecords: List<OutCome>
)