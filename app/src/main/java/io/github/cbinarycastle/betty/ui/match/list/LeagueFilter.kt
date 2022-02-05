package io.github.cbinarycastle.betty.ui.match.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.league.leagues
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun LeagueFilter(
    leagueFilters: List<LeagueFilterModel>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { Spacer(Modifier.width(HorizontalSpace)) }
        itemsIndexed(leagueFilters) { index, item ->
            LeagueFilterChip(
                index = index,
                leagueFilter = item,
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
    leagueFilter: LeagueFilterModel,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                BettyTheme.colors.primary
            } else {
                BettyTheme.colors.onSurface.copy(alpha = BorderAlpha)
            }
        ),
    ) {
        Row(
            modifier = Modifier
                .height(LeagueFilterChipHeight)
                .clickable { onClick(index) }
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leagueFilter is LeagueFilterModel.League) {
                GlideImage(
                    imageModel = leagueFilter.imageUrl,
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Fit,
                    previewPlaceholder = R.drawable.premier_league
                )
                Spacer(Modifier.width(4.dp))
            }
            Text(
                text = leagueFilter.name,
                color = if (selected) {
                    BettyTheme.colors.primary
                } else {
                    Color.Unspecified
                },
                style = BettyTheme.typography.caption
            )
        }
    }
}

@Composable
fun LeagueFilterPlaceholder(modifier: Modifier = Modifier) {
    val data = listOf(LeagueFilterModel.All) + leagues.map {
        LeagueFilterModel.League(
            id = it.id,
            name = it.name,
            imageUrl = it.imageUrl,
        )
    }

    Row(
        modifier = modifier.wrapContentWidth(
            align = Alignment.Start,
            unbounded = true
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(Modifier.width(HorizontalSpace))
        data.forEachIndexed { index, leagueFilter ->
            LeagueFilterChip(
                index = index,
                leagueFilter = leagueFilter,
                onClick = {},
                modifier = Modifier.placeholder(
                    visible = true,
                    color = Color.LightGray,
                    shape = CircleShape,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                )
            )
        }
        Spacer(Modifier.width(HorizontalSpace))
    }
}

@Preview
@Composable
private fun LeagueFilterPreview() {
    BettyTheme {
        LeagueFilter(
            leagueFilters = listOf(LeagueFilterModel.All) + leagues.map {
                LeagueFilterModel.League(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                )
            },
            selectedIndex = 0,
            onSelect = {}
        )
    }
}

private const val BorderAlpha = 0.12f
private val LeagueFilterChipHeight = 40.dp
private val HorizontalSpace = 8.dp