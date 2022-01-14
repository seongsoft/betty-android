package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun RankingList(ranking: Ranking) {
    Row {
        Column {
            FixedRankingHeader()
            ranking.rows.forEach { FixedRankingItem(it) }
        }
        Column(Modifier.horizontalScroll(state = rememberScrollState())) {
            ScrollableRankingHeader()
            ranking.rows.forEach { ScrollableRankingItem(it) }
        }
    }
}

@Composable
private fun FixedRankingHeader() {
    Row {
        RankingCell(
            text = stringResource(R.string.ranking_num),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = stringResource(R.string.ranking_team),
            horizontalArrangement = Arrangement.Start,
        )
    }
}

@Composable
private fun FixedRankingItem(row: Ranking.Row) {
    Row {
        RankingCell(
            text = row.number.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
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
private fun ScrollableRankingItem(row: Ranking.Row) {
    Row {
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
private fun RankingListPreview() {
    MacaoTheme {
        RankingList(ranking = matchDetails.ranking)
    }
}

private object RankingCellDefaults {
    val Width = 50.dp
}