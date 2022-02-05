package io.github.cbinarycastle.macao.entity

import org.threeten.bp.ZonedDateTime

data class MatchOverall(
    val id: Long,
    val matchAt: ZonedDateTime,
    val league: League,
    val homeTeam: Team,
    val awayTeam: Team,
    val suggestionInfo: SuggestionInfo,
) {
    data class League(
        val name: String,
        val imageUrl: String,
    )
}