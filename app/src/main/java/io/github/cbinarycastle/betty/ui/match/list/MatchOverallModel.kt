package io.github.cbinarycastle.betty.ui.match.list

import io.github.cbinarycastle.betty.entity.MatchOverall
import org.threeten.bp.ZonedDateTime

sealed interface MatchOverallModel {

    data class Item(val matchOverall: MatchOverall) : MatchOverallModel

    data class Separator(val matchAt: ZonedDateTime) : MatchOverallModel
}