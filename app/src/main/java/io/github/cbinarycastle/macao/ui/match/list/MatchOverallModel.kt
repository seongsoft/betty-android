package io.github.cbinarycastle.macao.ui.match.list

import io.github.cbinarycastle.macao.entity.MatchOverall
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime

sealed interface MatchOverallModel {

    data class Item(val matchOverall: MatchOverall) : MatchOverallModel

    data class Separator(val matchAt: ZonedDateTime) : MatchOverallModel
}