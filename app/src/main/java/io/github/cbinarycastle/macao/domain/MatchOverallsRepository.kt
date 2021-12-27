package io.github.cbinarycastle.macao.domain

import androidx.paging.PagingData
import io.github.cbinarycastle.macao.entity.MatchOverall
import kotlinx.coroutines.flow.Flow

interface MatchOverallsRepository {

    fun getMatchOveralls(): Flow<PagingData<MatchOverall>>
}