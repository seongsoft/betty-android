package io.github.cbinarycastle.macao.data

import org.threeten.bp.LocalDateTime

data class GetMatchesResponse(
    val data: List<MatchDto>,
) {
    data class MatchDto(
        val id: Long,
        /**
         * UTC Timezone
         */
        val matchDateTime: LocalDateTime,
        val leagueDto: LeagueDto,
        val homeTeamDto: TeamDto,
        val awayTeamDto: TeamDto,
        val suggestionDto: SuggestionDto,
    )

    data class LeagueDto(
        val leagueName: String,
        val leagueImageUrl: String,
    )

    data class TeamDto(
        val teamName: String,
        val teamImageUrl: String,
        /**
         * 최근 경기 결과 (WIN, DRAW, LOSE)
         * - 5개까지 보여줌 (7개까지도 가능)
         */
        val lastOutcomes: List<String>
    )

    data class SuggestionDto(
        /**
         * 예측 스코어 정보
         */
        val homeExpectedScore: Int,
        val awayExpectedScore: Int,
        /**
         * 예측 % 정보
         */
        val homeExpectedPercent: Int,
        val drawExpectedPercent: Int,
        val awayExpectedPercent: Int,
        /**
         * 예측 정보 (HOME_WIN, DRAW, AWAY_WIN)
         * - Expected Percent 가 가장 높은 쪽으로 결정됨
         * - Expected Score 가 동점인 경우 DRAW 도 포함됨
         */
        val suggestions: List<String>
    )
}