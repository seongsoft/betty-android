package io.github.cbinarycastle.macao.data.match.list

import org.threeten.bp.LocalDateTime
import retrofit2.http.Query

data class GetMatchesRequest(
    val baseDateTime: LocalDateTime,
    val page: Int,
    val size: Int,
    val leagueId: Long? = null,
)