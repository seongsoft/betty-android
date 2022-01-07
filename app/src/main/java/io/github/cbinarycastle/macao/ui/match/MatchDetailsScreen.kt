package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.matchDetails
import io.github.cbinarycastle.macao.data.matchOveralls
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.entity.*
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun MatchDetailsScreen(viewModel: MatchDetailsViewModel) {
    val result by viewModel.matchDetails.collectAsState(Result.Loading)
    MatchDetailsScreen(matchDetailsResult = result)
}

@Composable
private fun MatchDetailsScreen(matchDetailsResult: Result<MatchDetails>) {
    when (matchDetailsResult) {
        is Result.Success -> {
            val matchDetails = matchDetailsResult.data
            MatchDetailsScreen(matchDetails)
        }
        is Result.Error -> {}
        Result.Loading -> CircularProgressIndicator()
    }
}

@Composable
private fun MatchDetailsScreen(matchDetails: MatchDetails) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    var selectedTab by remember { mutableStateOf(MATCH_RECOMMENDATION_TAB) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Team(teamInfo = matchDetails.homeTeamInfo)
            Team(teamInfo = matchDetails.awayTeamInfo)
        }

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MacaoTheme.colors.surface,
            edgePadding = 0.dp,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        selectedTab = tab
                    },
                    modifier = Modifier.size(56.dp)
                ) {
                    Text(tab)
                }
            }
        }

        when (selectedTab) {
            MATCH_RECOMMENDATION_TAB -> MatchRecommendationList(matchDetails.recommendations)
            RELATIVE_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.relativeMatchHistories)
            HOME_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.homeTeamMatchHistories)
            AWAY_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.awayTeamMatchHistories)
            RANKING_TAB -> RankingList(matchDetails.ranking)
        }
    }
}

@Composable
private fun Team(
    teamInfo: TeamInfo,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = teamInfo.logoUrl,
            modifier = Modifier.size(60.dp),
            previewPlaceholder = R.drawable.manchester_united,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = teamInfo.teamName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MacaoTheme.typography.subtitle2
        )
        Spacer(Modifier.height(4.dp))
        Row {
            teamInfo.recentRecords.forEach {
                Spacer(Modifier.width(2.dp))
                RecentRecordStatus(it)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}

@Composable
private fun MatchRecommendationList(
    recommendations: List<MatchRecommendation>,
) {
}

@Composable
private fun MatchHistoryList(histories: List<MatchHistory>) {
    Column {
        histories.forEach {
            MatchHistoryItem(it)
            Divider()
        }
    }
}

@Composable
private fun MatchHistoryItem(history: MatchHistory) {
    Row(
        Modifier
            .padding(vertical = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = history.leagueName,
                style = MacaoTheme.typography.caption,
            )
            Text(
                text = history.matchedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                style = MacaoTheme.typography.caption,
            )
        }
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = history.homeTeamName,
                    style = MacaoTheme.typography.subtitle2,
                )
                Text(
                    text = history.homeTeamScore.toString(),
                    style = MacaoTheme.typography.subtitle2,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = history.awayTeamName,
                    style = MacaoTheme.typography.subtitle2,
                )
                Text(
                    text = history.awayTeamScore.toString(),
                    style = MacaoTheme.typography.subtitle2,
                )
            }
        }
    }
}

@Composable
private fun RankingList(ranking: Ranking) {
    ranking.group.forEach { group ->
        Row {
            Column {
                FixedRankingHeader()
                group.items.forEach { item -> FixedRankingItem(item) }
            }
            Column(Modifier.horizontalScroll(state = rememberScrollState())) {
                ScrollableRankingHeader()
                group.items.forEach { item -> ScrollableRankingItem(item) }
            }
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
private fun FixedRankingItem(item: Ranking.RankingInfo) {
    Row {
        RankingCell(
            text = item.rankingNum.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.teamName,
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
private fun ScrollableRankingItem(item: Ranking.RankingInfo) {
    Row {
        RankingCell(
            text = item.matches.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.gamePoint.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.win.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.draw.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.lose.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.score.toString(),
            modifier = Modifier.width(RankingCellDefaults.Width)
        )
        RankingCell(
            text = item.losePoint.toString(),
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
fun MatchDetailsScreenPreview() {
    MacaoTheme {
        MatchDetailsScreen(matchDetails = matchDetails)
    }
}

@Preview
@Composable
fun RelativeMatchHistoryListPreview() {
    MacaoTheme {
        MatchHistoryList(histories = matchDetails.relativeMatchHistories)
    }
}

@Preview
@Composable
fun RankingListPreview() {
    MacaoTheme {
        RankingList(ranking = matchDetails.ranking)
    }
}

private object RankingCellDefaults {
    val Width = 50.dp
}

private const val MATCH_RECOMMENDATION_TAB = "추천"
private const val RELATIVE_MATCH_HISTORY_TAB = "상대 전적"
private const val HOME_TEAM_MATCH_HISTORY_TAB = "홈팀 전적"
private const val AWAY_TEAM_MATCH_HISTORY_TAB = "원정팀 전적"
private const val RANKING_TAB = "순위"

private val tabs = listOf(
    MATCH_RECOMMENDATION_TAB,
    RELATIVE_MATCH_HISTORY_TAB,
    HOME_TEAM_MATCH_HISTORY_TAB,
    AWAY_TEAM_MATCH_HISTORY_TAB,
    RANKING_TAB,
)