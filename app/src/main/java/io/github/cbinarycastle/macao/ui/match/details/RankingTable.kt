package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.entity.Ranking
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import io.github.cbinarycastle.macao.util.border

@Composable
fun RankingTable(
    ranking: Ranking,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier.verticalScroll(rememberScrollState())) {
        Column(Modifier.width(IntrinsicSize.Max)) {
            FixedRankingHeader(Modifier.fillMaxWidth())
            FixedRankingItems(
                rows = ranking.rows,
                homeTeamName = homeTeamName,
                awayTeamName = awayTeamName,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(Modifier.horizontalScroll(state = rememberScrollState())) {
            ScrollableRankingHeader()
            ScrollableRankingItems(
                rows = ranking.rows,
                homeTeamName = homeTeamName,
                awayTeamName = awayTeamName,
            )
        }
    }
}

@Composable
private fun FixedRankingHeader(modifier: Modifier = Modifier) {
    Row(modifier) {
        Spacer(Modifier.width(RankingCellDefaults.RankingNumberWidth))
        RankingCell(
            text = stringResource(R.string.ranking_team),
            horizontalArrangement = Arrangement.Start,
        )
    }
}

@Composable
private fun FixedRankingItems(
    rows: List<Ranking.Row>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    rows.forEach {
        FixedRankingItem(
            row = it,
            modifier = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                modifier
                    .border(
                        start = 1.dp,
                        top = 1.dp,
                        end = 0.dp,
                        bottom = 1.dp,
                        color = MacaoTheme.colors.primary
                    )
                    .background(
                        color = MacaoTheme.colors.primary.copy(alpha = RankingCellDefaults.HighlightAlpha)
                    )
            } else {
                modifier
            }
        )
    }
}

@Composable
private fun FixedRankingItem(
    row: Ranking.Row,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        RankingCell(
            text = row.number.toString(),
            modifier = Modifier.width(RankingCellDefaults.RankingNumberWidth)
        )
        RankingCell(
            text = row.teamName,
            modifier = Modifier.padding(end = 8.dp),
            horizontalArrangement = Arrangement.Start,
            textStyle = MacaoTheme.typography.subtitle2,
        )
    }
}

@Composable
private fun ScrollableRankingHeader() {
    Row {
        RankingCell(
            text = stringResource(R.string.ranking_matches),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_game_point),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_win),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_draw),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_lose),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_score),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_lose_point),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
    }
}

@Composable
private fun ScrollableRankingItems(
    rows: List<Ranking.Row>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    rows.forEach {
        ScrollableRankingItem(
            row = it,
            modifier = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                modifier
                    .border(
                        start = 0.dp,
                        top = 1.dp,
                        end = 1.dp,
                        bottom = 1.dp,
                        color = MacaoTheme.colors.primary
                    )
                    .background(
                        color = MacaoTheme.colors.primary.copy(alpha = RankingCellDefaults.HighlightAlpha)
                    )
            } else {
                modifier
            }
        )
    }
}

@Composable
private fun ScrollableRankingItem(
    row: Ranking.Row,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        RankingCell(
            text = row.matchCount.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = row.points.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = row.winCount.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = row.drawCount.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = row.loseCount.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = row.goalFor.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = row.goalAgainst.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
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

private object RankingCellDefaults {
    const val HighlightAlpha = 0.15f
    val Width = 50.dp
    val RankingNumberWidth = 36.dp
}