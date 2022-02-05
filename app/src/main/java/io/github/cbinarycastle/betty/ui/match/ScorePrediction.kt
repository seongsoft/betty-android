package io.github.cbinarycastle.betty.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun ScorePrediction(
    homeScore: Int,
    awayScore: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = homeScore.toString(),
            color = if (homeScore > awayScore) {
                BettyTheme.extendedColors.win
            } else {
                BettyTheme.colors.onSurface
            },
            style = BettyTheme.typography.h6
        )
        Spacer(Modifier.width(4.dp))
        Text(":")
        Spacer(Modifier.width(4.dp))
        Text(
            text = awayScore.toString(),
            color = if (awayScore > homeScore) {
                BettyTheme.extendedColors.win
            } else {
                BettyTheme.colors.onSurface
            },
            style = BettyTheme.typography.h6
        )
    }
}