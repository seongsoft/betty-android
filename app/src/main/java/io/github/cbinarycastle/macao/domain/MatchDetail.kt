package io.github.cbinarycastle.macao.domain

data class MatchDetail(
    val id: String,
    val homeTeamInfo: TeamInfo,
    val awayTeamInfo: TeamInfo,
    val recommendation: List<MatchRecommendation>,              //추천은 복수개가 될 수 있음
    val relativeMatchHistories: List<MatchHistory>,             //상대 전적
    val homeTeamMatchHistories: List<MatchHistory>,             //홈팀 전적
    val awayTeamMatchHistories: List<MatchHistory>,             //원정팀 전적
    var rankingInfo: Map<String, List<RankingInfo>>?,           //순위 정보 (형태 달라지 질 수 있어 아직 Fix 아님)
)