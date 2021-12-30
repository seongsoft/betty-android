package io.github.cbinarycastle.macao.entity

import org.threeten.bp.LocalDateTime

data class MatchHistory(
    val leagueName: String,
    val matchedAt: LocalDateTime,
    val homeTeamName: String,
    val homeTeamScore: Int,
    val awayTeamName: String,
    val awayTeamScore: Int,
    val handi: String,              //핸디캡 (아직 Fix아님)
    val handiOutcome: String,       //햔디캡 결과 (아직 Fix아님)
    val isOdd: Boolean,            //홀짝 (홀수 true)
    val firstHarfScore: String,     //전반 스코어
)