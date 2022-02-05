package io.github.cbinarycastle.betty

data class PlaceDto(
    val homeDto: ValueDto,
    val awayDto: ValueDto
) {
    data class ValueDto(
        /**
         * 경기 수
         */
        val totalMatchCount: Int,
        /**
         * 승 수
         */
        val winMatchCount: Int,
        /**
         * 무승부 수
         */
        val drawMatchCount: Int,
        /**
         * 패 수
         */
        val loseMatchCount: Int,
        /**
         * 득점
         */
        val goalFor: Int,
        /**
         * 실점
         */
        val goalAgainst: Int,
        /**
         * 승점
         */
        val points: Int,
    )
}