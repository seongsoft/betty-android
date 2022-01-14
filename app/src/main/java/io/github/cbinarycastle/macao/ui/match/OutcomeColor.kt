package io.github.cbinarycastle.macao.ui.match

import androidx.compose.runtime.Composable
import io.github.cbinarycastle.macao.entity.Outcome
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun Outcome.color() = when (this) {
    Outcome.WIN -> MacaoTheme.extendedColors.win
    Outcome.DRAW -> MacaoTheme.extendedColors.draw
    Outcome.LOSE -> MacaoTheme.extendedColors.lose
}