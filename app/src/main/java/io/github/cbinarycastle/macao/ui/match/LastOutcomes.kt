package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.entity.Outcome

@Composable
fun LastOutcomes(outcome: Outcome) {
    Surface(
        modifier = Modifier.size(12.dp),
        shape = CircleShape,
        color = outcome.color(),
        contentColor = Color.White,
        content = {}
    )
}