package io.github.cbinarycastle.betty.data.match.details

data class GoalPerMatchDto(
    val number: Int,
    val teamName: String,
    val teamImageUrl: String,
    val matchCount: Int,
    val gameCount0: Int,
    val gameCount1: Int,
    val gameCount2: Int,
    val gameCount3: Int,
    val gameCount4: Int,
    val gameCount5: Int,
    val gameCount6: Int,
    val gameCountEtc: Int,
    val goalPerMatchAvg: Double,
)