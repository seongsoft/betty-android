package io.github.cbinarycastle.betty.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.betty.entity.Outcome
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun LastOutcome(outcome: Outcome) {
    Surface(
        shape = CircleShape,
        color = outcome.color(),
        contentColor = Color.White,
    ) {
        Box(
            modifier = Modifier.size(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = outcome.text(),
                style = BettyTheme.typography.overline
            )
        }
    }
}

@Preview
@Composable
fun LastOutcomePreview() {
    BettyTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            LastOutcome(Outcome.WIN)
            LastOutcome(Outcome.DRAW)
            LastOutcome(Outcome.LOSE)
        }
    }
}