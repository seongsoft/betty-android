package io.github.cbinarycastle.betty.data.match.overall

import org.threeten.bp.LocalDateTime

data class GetMatchesRequest(
    val baseDateTime: LocalDateTime,
    val page: Int,
    val size: Int,
    val leagueId: Long? = null,
)