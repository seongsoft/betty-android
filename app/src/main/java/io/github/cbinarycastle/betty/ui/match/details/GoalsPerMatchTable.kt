package io.github.cbinarycastle.betty.ui.match.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.match.details.matchDetails
import io.github.cbinarycastle.betty.entity.GoalsPerMatch
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoalsPerMatchTable(
    goalsPerMatches: List<GoalsPerMatch>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    val sharedHorizontalScrollState = rememberScrollState()

    LazyColumn(modifier) {
        stickyHeader {
            GoalsPerMatchHeader(horizontalScrollState = sharedHorizontalScrollState)
            Divider()
        }
        items(goalsPerMatches) {
            GoalsPerMatchItem(
                goalsPerMatch = it,
                scrollState = sharedHorizontalScrollState,
                modifier = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                    modifier
                        .border(
                            width = 1.dp,
                            color = BettyTheme.colors.primary
                        )
                        .background(color = BettyTheme.colors.primary.copy(alpha = HighlightAlpha))
                } else {
                    modifier
                }
            )
            Divider(
                Modifier
                    .alpha(ContentAlpha.disabled)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun GoalsPerMatchHeader(horizontalScrollState: ScrollState) {
    Row(Modifier.background(BettyTheme.colors.background)) {
        Spacer(Modifier.width(DefaultCellWidth))
        GoalsPerMatchCell(
            text = stringResource(R.string.goals_per_match_team),
            modifier = Modifier.width(TeamCellWidth),
            horizontalArrangement = Arrangement.Start,
        )
        Row(Modifier.horizontalScroll(horizontalScrollState)) {
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_matches),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_zero_goal),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_one_goal),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_two_goals),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_three_goals),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_four_goals),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_five_goals),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_six_goals),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_etc_goals),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = stringResource(R.string.goals_per_match_average),
                modifier = Modifier.width(DefaultCellWidth)
            )
        }
    }
}

@Composable
private fun GoalsPerMatchItem(
    goalsPerMatch: GoalsPerMatch,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GoalsPerMatchCell(
            text = goalsPerMatch.number.toString(),
            modifier = Modifier.width(DefaultCellWidth)
        )
        Row(
            modifier = Modifier.width(TeamCellWidth),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                imageModel = goalsPerMatch.teamImageUrl,
                modifier = Modifier.size(24.dp),
                previewPlaceholder = R.drawable.manchester_united
            )
            Spacer(Modifier.width(4.dp))
            GoalsPerMatchCell(
                text = goalsPerMatch.teamName,
                modifier = Modifier.width(TeamCellWidth),
                horizontalArrangement = Arrangement.Start,
                textStyle = BettyTheme.typography.subtitle2,
            )
        }
        Row(Modifier.horizontalScroll(scrollState)) {
            GoalsPerMatchCell(
                text = goalsPerMatch.matchCount.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount0.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount1.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount2.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount3.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount4.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount5.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCount6.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.gameCountEtc.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            GoalsPerMatchCell(
                text = goalsPerMatch.average.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
        }
    }
}

@Composable
private fun GoalsPerMatchCell(
    text: String,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    textStyle: TextStyle = BettyTheme.typography.body2,
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = horizontalArrangement,
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}

@Preview
@Composable
private fun GoalsPerMatchTablePreview() {
    BettyTheme {
        GoalsPerMatchTable(
            goalsPerMatches = matchDetails.goalsPerMatches,
            homeTeamName = "Manchester City",
            awayTeamName = "Liverpool",
        )
    }
}

private const val HighlightAlpha = 0.15f
private val DefaultCellWidth = 36.dp
private val TeamCellWidth = 164.dp
