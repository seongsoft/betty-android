package io.github.cbinarycastle.macao.event

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

sealed class Event(
    val type: String,
    val properties: Map<String, Any>,
) {
    class MatchesLeagueFilterClick(leagueName: String) : Event(
        type = "matches_leagueFilter_click",
        properties = mapOf("league" to leagueName)
    )

    class MatchesMatchItemClick(
        matchId: Long,
        matchAt: LocalDateTime,
        homeTeamName: String,
        awayTeamName: String,
    ) : Event(
        type = "matches_matchItem_click",
        properties = mapOf(
            "matchId" to matchId,
            "matchAt" to matchAt.format(DateTimeFormatter.ISO_DATE_TIME),
            "homeTeamName" to homeTeamName,
            "awayTeamName" to awayTeamName,
        )
    )

    class MatchDetailsTabClick(tabName: String) : Event(
        type = "matchDetails_place_tab_click",
        properties = mapOf("tabName" to tabName)
    )
}