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
import io.github.cbinarycastle.macao.entity.Ranking
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun RankingTable(
    ranking: Ranking,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    val sharedHorizontalScrollState = rememberScrollState()

    Column(modifier) {
        RankingHeader(horizontalScrollState = sharedHorizontalScrollState)
        Divider()
        RankingItems(
            rows = ranking.rows,
            homeTeamName = homeTeamName,
            awayTeamName = awayTeamName,
            horizontalScrollState = sharedHorizontalScrollState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun RankingHeader(
    horizontalScrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Spacer(Modifier.width(NumberCellWidth))
        RankingCell(
            text = stringResource(R.string.ranking_team),
            modifier = Modifier.width(TeamCellWidth),
            horizontalArrangement = Arrangement.Start,
        )
        Row(Modifier.horizontalScroll(horizontalScrollState)) {
            RankingCell(
                text = stringResource(R.string.ranking_matches),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = stringResource(R.string.ranking_game_point),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = stringResource(R.string.ranking_win),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = stringResource(R.string.ranking_draw),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = stringResource(R.string.ranking_lose),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = stringResource(R.string.ranking_score),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = stringResource(R.string.ranking_lose_point),
                modifier = Modifier.width(DefaultCellWidth)
            )
        }
    }
}

@Composable
private fun RankingItems(
    rows: List<Ranking.Row>,
    homeTeamName: String,
    awayTeamName: String,
    horizontalScrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    rows.forEach {
        RankingItem(
            row = it,
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
private fun RankingItem(
    row: Ranking.Row,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RankingCell(
            text = row.number.toString(),
            modifier = Modifier.width(NumberCellWidth)
        )
        RankingCell(
            text = row.teamName,
            modifier = Modifier.width(TeamCellWidth),
            horizontalArrangement = Arrangement.Start,
            textStyle = MacaoTheme.typography.subtitle2,
        )
        Row(Modifier.horizontalScroll(scrollState)) {
            RankingCell(
                text = row.matchCount.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = row.points.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = row.winCount.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = row.drawCount.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = row.loseCount.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = row.goalFor.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
            RankingCell(
                text = row.goalAgainst.toString(),
                modifier = Modifier.width(DefaultCellWidth)
            )
        }
    }
}

@Composable
private fun RankingCell(
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
private fun RankingTablePreview() {
    MacaoTheme {
        RankingTable(
            ranking = matchDetails.ranking,
            homeTeamName = "Manchester City",
            awayTeamName = "Liverpool",
        )
    }
}

private const val HighlightAlpha = 0.15f
private val DefaultCellWidth = 50.dp
private val NumberCellWidth = 36.dp
private val TeamCellWidth = 136.dp
