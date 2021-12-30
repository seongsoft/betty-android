package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.entity.Ranking

fun MatchDetailsResponse.Ranking.toEntity() = Ranking(
    type = type.toEntity(),
    group = group.map { it.toEntity() }
)