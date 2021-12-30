package io.github.cbinarycastle.macao.entity

data class Ranking(
    val type: RankingType,
    val group: List<RankingGroup>,
) {
    enum class RankingType {
        LEAGUE, TOURNAMENT
    }

    data class RankingGroup(
        val name: String?,
        val items: List<RankingInfo>,
    )

    data class RankingInfo(
        val rankingNum: Int,
        val teamName: String,
        val matches: Int,
        val win: Int,
        val draw: Int,
        val lose: Int,
        val score: Int,
        val losePoint: Int,
        val gamePoint: Int,
    )
}