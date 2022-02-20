package io.github.cbinarycastle.betty.ui.match.list

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import io.github.cbinarycastle.betty.entity.MatchOverall
import org.threeten.bp.ZoneId

fun PagingData<MatchOverall>.insertSeparators(
    timeZoneId: ZoneId = ZoneId.systemDefault()
): PagingData<MatchOverallModel> {
    return this
        .map { MatchOverallModel.Item(it) }
        .insertSeparators { before, after ->
            when {
                after == null -> null
                before == null -> MatchOverallModel.Separator(
                    after.matchOverall.matchAt.withZoneSameInstant(timeZoneId)
                )
                else -> {
                    if (
                        before.matchOverall.matchAt
                            .withZoneSameInstant(timeZoneId)
                            .toLocalDate() ==
                        after.matchOverall.matchAt
                            .withZoneSameInstant(timeZoneId)
                            .toLocalDate()
                    ) {
                        null
                    } else {
                        MatchOverallModel.Separator(
                            after.matchOverall.matchAt.withZoneSameInstant(timeZoneId)
                        )
                    }
                }
            }
        }
}