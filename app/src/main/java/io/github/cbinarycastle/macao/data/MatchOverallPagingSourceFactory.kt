package io.github.cbinarycastle.macao.data

import androidx.paging.PagingSource
import io.github.cbinarycastle.macao.entity.MatchOverall

interface MatchOverallPagingSourceFactory {

    fun create(): PagingSource<Int, MatchOverall>
}