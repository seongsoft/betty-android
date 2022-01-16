package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

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
                MacaoTheme.extendedColors.win
            } else {
                MacaoTheme.colors.onSurface
            },
            style = MacaoTheme.typography.h6
        )
        Spacer(Modifier.width(4.dp))
        Text(":")
        Spacer(Modifier.width(4.dp))
        Text(
            text = awayScore.toString(),
            color = if (awayScore > homeScore) {
                MacaoTheme.extendedColors.win
            } else {
                MacaoTheme.colors.onSurface
            },
            style = MacaoTheme.typography.h6
        )
    }
}