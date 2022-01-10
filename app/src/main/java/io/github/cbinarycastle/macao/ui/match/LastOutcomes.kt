package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.entity.Outcome
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun LastOutcomes(outcome: Outcome) {
    Surface(
        modifier = Modifier.size(12.dp),
        shape = CircleShape,
        color = outcome.backgroundColor(),
        contentColor = Color.White,
    ) {}
}

@Composable
private fun Outcome.backgroundColor() = when (this) {
    Outcome.WIN -> MacaoTheme.extendedColors.success
    Outcome.DRAW -> MacaoTheme.extendedColors.neutral
    Outcome.LOSE -> MacaoTheme.colors.error
}