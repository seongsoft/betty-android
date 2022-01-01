package io.github.cbinarycastle.macao.ui.match

import io.github.cbinarycastle.macao.entity.MatchOverall
import org.threeten.bp.LocalDateTime

sealed interface MatchOverallModel {

    data class Item(val matchOverall: MatchOverall) : MatchOverallModel

    data class Separator(val matchAt: LocalDateTime) : MatchOverallModel
}