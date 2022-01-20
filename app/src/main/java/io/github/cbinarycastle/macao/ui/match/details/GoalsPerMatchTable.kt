package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.entity.GoalsPerMatch
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun GoalsPerMatchTable(
    goalsPerMatches: List<GoalsPerMatch>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    val sharedHorizontalScrollState = rememberScrollState()

    Column(modifier.verticalScroll(rememberScrollState())) {
        GoalsPerMatchHeader(horizontalScrollState = sharedHorizontalScrollState)
        Divider()
        GoalsPerMatchItems(
            goalsPerMatches = goalsPerMatches,
            homeTeamName = homeTeamName,
            awayTeamName = awayTeamName,
            horizontalScrollState = sharedHorizontalScrollState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun GoalsPerMatchHeader(
    horizontalScrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
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
private fun GoalsPerMatchItems(
    goalsPerMatches: List<GoalsPerMatch>,
    homeTeamName: String,
    awayTeamName: String,
    horizontalScrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    goalsPerMatches.forEach {
        GoalsPerMatchItem(
            goalsPerMatch = it,
            scrollState = horizontalScrollState,
            modifier = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                modifier
                    .border(
                        width = 1.dp,
                        color = MacaoTheme.colors.primary
                    )
                    .background(color = MacaoTheme.colors.primary.copy(alpha = HighlightAlpha))
            } else {
                modifier
            }
        )
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
        GoalsPerMatchCell(
            text = goalsPerMatch.teamName,
            modifier = Modifier.width(TeamCellWidth),
            horizontalArrangement = Arrangement.Start,
            textStyle = MacaoTheme.typography.subtitle2,
        )
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
    textStyle: TextStyle = MacaoTheme.typography.body2,
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
    MacaoTheme {
        GoalsPerMatchTable(
            goalsPerMatches = matchDetails.goalsPerMatches,
            homeTeamName = "Manchester City",
            awayTeamName = "Liverpool",
        )
    }
}

private const val HighlightAlpha = 0.15f
private val DefaultCellWidth = 36.dp
private val TeamCellWidth = 136.dp
