package io.github.cbinarycastle.macao.data

import org.threeten.bp.LocalDateTime

data class MatchDetailsResponse(
    val id: String,
    val homeTeamInfo: TeamInfo,
    val awayTeamInfo: TeamInfo,
    val recommendation: List<MatchRecommendation>,
    val relativeMatchHistories: List<MatchHistory>,
    val homeTeamMatchHistories: List<MatchHistory>,
    val awayTeamMatchHistories: List<MatchHistory>,
    val ranking: Ranking,
) {
    data class MatchRecommendation(
        val description: String,
        val recommendType: RecommendType
    )

    data class MatchHistory(
        val leagueName: String,
        val matchedAt: LocalDateTime,
        val homeTeamName: String,
        val homeTeamScore: Int,
        val rightTeamName: String,
        val awayTeamScore: Int,
        val handi: String,
        val handiOutcome: String,
        val isOdd: Boolean,
        val firstHalfScore: String,
    )

    enum class RankingType {
        LEAGUE, TOURNAMENT
    }

    data class Ranking(
        val type: RankingType,
        val group: List<RankingGroup>,
    )

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