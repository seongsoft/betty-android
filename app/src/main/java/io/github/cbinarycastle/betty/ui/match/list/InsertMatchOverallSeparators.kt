package io.github.cbinarycastle.betty.ui.match.list

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import io.github.cbinarycastle.betty.entity.MatchOverall

fun PagingData<MatchOverall>.insertSeparators(): PagingData<MatchOverallModel> {
    return this
        .map { MatchOverallModel.Item(it) }
        .insertSeparators { before, after ->
            when {
                after == null -> null
                before == null -> MatchOverallModel.Separator(after.matchOverall.matchAt)
                else -> {
                    if (
                        before.matchOverall.matchAt.toLocalDate() ==
                        after.matchOverall.matchAt.toLocalDate()
                    ) {
                        null
                    } else {
                        MatchOverallModel.Separator(after.matchOverall.matchAt)
                    }
                }
            }
        }
}