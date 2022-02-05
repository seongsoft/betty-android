package io.github.cbinarycastle.betty.ui.match

import androidx.compose.runtime.Composable
import io.github.cbinarycastle.betty.entity.Outcome
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun Outcome.color() = when (this) {
    Outcome.WIN -> BettyTheme.extendedColors.win
    Outcome.DRAW -> BettyTheme.extendedColors.draw
    Outcome.LOSE -> BettyTheme.extendedColors.lose
}