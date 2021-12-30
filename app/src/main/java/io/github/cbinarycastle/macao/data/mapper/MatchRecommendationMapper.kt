package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.entity.MatchRecommendation
import io.github.cbinarycastle.macao.entity.RecommendType

fun MatchDetailsResponse.MatchRecommendation.toEntity() = MatchRecommendation(
    description = description,
    recommendType = RecommendType.valueOf(recommendType.name),
)
