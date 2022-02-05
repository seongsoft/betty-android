package io.github.cbinarycastle.macao.event

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

sealed class Event(
    val type: String,
    val properties: Map<String, Any?> = emptyMap(),
) {
    class MatchesLeagueFilterClick(leagueName: String) : Event(
        type = "matches_leagueFilter_click",
        properties = mapOf("league" to leagueName)
    )

    class MatchesLeaguesLoadFailed : Event(
        type = "matches_leagueFilter_loadFailed",
    )

    class MatchesMatchesLoadFailed(leagueName: String?) : Event(
        type = "matches_matches_loadFailed",
        properties = mapOf("league" to leagueName)
    )

    class MatchesTeamImageLoadFailed(matchId: Long, imageUrl: String) : Event(
        type = "matches_teamImage_loadFailed",
        properties = mapOf(
            "matchId" to matchId,
            "imageUrl" to imageUrl,
        )
    )

    class MatchesMatchItemClick(
        matchId: Long,
        matchAt: ZonedDateTime,
        homeTeamName: String,
        awayTeamName: String,
    ) : Event(
        type = "matches_matchItem_click",
        properties = mapOf(
            "matchId" to matchId,
            "matchAt" to matchAt.format(DateTimeFormatter.ISO_DATE_TIME),
            "homeTeam" to homeTeamName,
            "awayTeam" to awayTeamName,
        )
    )

    class MatchDetailsLoadFailed(matchId: Long) : Event(
        type = "matchDetails_loadFailed",
        properties = mapOf("matchId" to matchId)
    )

    class MatchDetailsTabClick(tabName: String) : Event(
        type = "matchDetails_tab_click",
        properties = mapOf("tab" to tabName)
    )
}