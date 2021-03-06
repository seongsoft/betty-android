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
import io.github.cbinarycastle.betty.entity.Ranking
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RankingTable(
    ranking: Ranking,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    val sharedHorizontalScrollState = rememberScrollState()

    LazyColumn(modifier) {
        stickyHeader {
            RankingHeader(horizontalScrollState = sharedHorizontalScrollState)
            Divider()
        }
        items(ranking.rows) {
            RankingItem(
                row = it,
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
private fun RankingHeader(horizontalScrollState: ScrollState) {
    Row(Modifier.background(BettyTheme.colors.background)) {
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
        Row(
            modifier = Modifier.width(TeamCellWidth),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                imageModel = row.teamImageUrl,
                modifier = Modifier.size(24.dp),
                previewPlaceholder = R.drawable.manchester_united
            )
            Spacer(Modifier.width(4.dp))
            RankingCell(
                text = row.teamName,
                horizontalArrangement = Arrangement.Start,
                textStyle = BettyTheme.typography.subtitle2,
            )
        }
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
private fun RankingTablePreview() {
    BettyTheme {
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
private val TeamCellWidth = 164.dp
