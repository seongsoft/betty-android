package io.github.cbinarycastle.macao.entity

data class MatchDetails(
    val id: String,
    val homeTeamInfo: TeamInfo,
    val awayTeamInfo: TeamInfo,
    val recommendations: List<MatchRecommendation>,              //추천은 복수개가 될 수 있음
    val relativeMatchHistories: List<MatchHistory>,             //상대 전적
    val homeTeamMatchHistories: List<MatchHistory>,             //홈팀 전적
    val awayTeamMatchHistories: List<MatchHistory>,             //원정팀 전적
    val ranking: Ranking,                                       //순위 정보 (형태 달라지 질 수 있어 아직 Fix 아님)
)