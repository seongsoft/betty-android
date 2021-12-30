package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.MatchDetailsResponse
import io.github.cbinarycastle.macao.entity.Ranking

fun MatchDetailsResponse.RankingType.toEntity() = Ranking.RankingType.valueOf(name)