package io.github.cbinarycastle.macao.ui.match.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun LeagueFilter(
    leagues: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { Spacer(Modifier.width(HorizontalSpace)) }
        itemsIndexed(leagues) { index, item ->
            LeagueFilterChip(
                index = index,
                league = item,
                onClick = onSelect,
                selected = index == selectedIndex
            )
        }
        item { Spacer(Modifier.width(HorizontalSpace)) }
    }
}

@Composable
private fun LeagueFilterChip(
    index: Int,
    league: String,
    onClick: (Int) -> Unit,
    selected: Boolean = false,
) {
    Surface(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MacaoTheme.colors.primary
            } else {
                MacaoTheme.colors.onSurface.copy(alpha = BorderAlpha)
            }
        ),
    ) {
        Text(
            text = league,
            modifier = Modifier
                .clickable { onClick(index) }
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            color = if (selected) {
                MacaoTheme.colors.primary
            } else {
                Color.Unspecified
            },
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
                "All",
                "Premier League",
                "Bundesliga",
                "LaLiga",
                "Serie A",
            ),
            selectedIndex = 0,
            onSelect = {}
        )
    }
}

private const val BorderAlpha = 0.12f
private val HorizontalSpace = 8.dp