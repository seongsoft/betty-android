package io.github.cbinarycastle.macao.data.match

data class TeamDto(
    val teamName: String,
    val teamImageUrl: String,
    /**
     * 최근 경기 결과 (WIN, DRAW, LOSE)
     * - 5개까지 보여줌 (7개까지도 가능)
     */
    val lastOutcomes: List<String>
)