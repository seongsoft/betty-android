package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.entity.Ranking
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun RankingList(ranking: Ranking) {
//    ranking.group.forEach { group ->
//        Row {
//            Column {
//                FixedRankingHeader()
//                group.items.forEach { item ->
//                    FixedRankingItem(item)
//                }
//            }
//            Column(Modifier.horizontalScroll(state = rememberScrollState())) {
//                ScrollableRankingHeader()
//                group.items.forEach { item -> ScrollableRankingItem(item) }
//            }
//        }
//    }
}

//@Composable
//private fun FixedRankingHeader() {
//    Row {
//        RankingCell(
//            text = stringResource(R.string.ranking_num),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_team),
//            horizontalArrangement = Arrangement.Start,
//        )
//    }
//}
//
//@Composable
//private fun FixedRankingItem(item: Ranking.RankingInfo) {
//    Row {
//        RankingCell(
//            text = item.rankingNum.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.teamName,
//            modifier = Modifier.padding(end = 8.dp),
//            horizontalArrangement = Arrangement.Start,
//            textStyle = MacaoTheme.typography.subtitle2,
//        )
//    }
//}
//
//@Composable
//private fun ScrollableRankingHeader() {
//    Row {
//        RankingCell(
//            text = stringResource(R.string.ranking_matches),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_game_point),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_win),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_draw),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_lose),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_score),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = stringResource(R.string.ranking_lose_point),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//    }
//}
//
//@Composable
//private fun ScrollableRankingItem(item: Ranking.RankingInfo) {
//    Row {
//        RankingCell(
//            text = item.matches.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.gamePoint.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.win.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.draw.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.lose.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.score.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//        RankingCell(
//            text = item.losePoint.toString(),
//            modifier = Modifier.width(RankingCellDefaults.Width)
//        )
//    }
//}
//
//@Composable
//private fun RankingCell(
//    text: String,
//    modifier: Modifier = Modifier,
//    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
//    textStyle: TextStyle = MacaoTheme.typography.body2,
//) {
//    Row(
//        modifier = modifier.padding(vertical = 8.dp),
//        horizontalArrangement = horizontalArrangement,
//    ) {
//        Text(
//            text = text,
//            style = textStyle,
//        )
//    }
//}
//
//private object RankingCellDefaults {
//    val Width = 50.dp
//}