package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun LeagueFilter(
    leagues: List<String>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(leagues) {
            LeagueFilterChip(
                league = it,
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun LeagueFilterChip(
    league: String,
    onClick: () -> Unit,
) {
    Surface(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MacaoTheme.colors.onSurface.copy(alpha = 0.12f)
        ),
    ) {
        Text(
            text = league,
            modifier = Modifier
                .clickable { onClick() }
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            style = MacaoTheme.typography.caption
        )
    }
}

@Preview
@Composable
private fun LeagueFilterPreview() {
    MacaoTheme {
        LeagueFilter(
            leagues = listOf(
                "Premier League",
                "Bundesliga",
                "LaLiga",
                "Serie A",
            ),
            onClick = {}
        )
    }
}