package io.github.cbinarycastle.betty.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.betty.entity.Outcome

@Composable
fun LastOutcomes(outcomes: List<Outcome>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        outcomes.asReversed().forEachIndexed { index, outcome ->
            Spacer(Modifier.width(2.dp))
            LastOutcome(
                outcome = outcome,
                modifier = Modifier.alpha(
                    if (index == outcomes.lastIndex) 1f else 0.5f
                ),
                contentModifier = if (index == outcomes.lastIndex) {
                    Modifier.size(20.dp)
                } else {
                    Modifier
                }
            )
            Spacer(Modifier.width(2.dp))
        }
    }
}