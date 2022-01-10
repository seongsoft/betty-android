package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.matchDetails
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.entity.MatchHistory
import io.github.cbinarycastle.macao.entity.Team
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
    var selectedTab by remember { mutableStateOf(SUMMARY_TAB) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Team(team = matchDetails.homeTeam)
            Team(team = matchDetails.awayTeam)
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
            SUMMARY_TAB -> SummaryList()
            RELATIVE_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.relativeMatchHistories)
            HOME_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.homeTeamMatchHistories)
            AWAY_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.awayTeamMatchHistories)
            RANKING_TAB -> RankingList(matchDetails.ranking)
        }
    }
}

@Composable
private fun Team(
    team: Team,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = team.imageUrl,
            modifier = Modifier.size(60.dp),
            previewPlaceholder = R.drawable.manchester_united,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = team.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MacaoTheme.typography.subtitle2
        )
        Spacer(Modifier.height(4.dp))
        Row {
            team.lastOutcomes.forEach {
                Spacer(Modifier.width(2.dp))
                LastOutcomes(it)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}

@Composable
private fun SummaryList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SummaryCard(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        SummaryCard(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        SummaryCard(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun SummaryCard(modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home Matches",
                style = MacaoTheme.typography.h6
            )
            Spacer(Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SummaryItem(
                    label = "경기",
                    homeTeamValue = 19,
                    awayTeamValue = 21
                )
                SummaryItem(
                    label = "승",
                    homeTeamValue = 9,
                    awayTeamValue = 17,
                    comparator = Comparator.naturalOrder()
                )
                SummaryItem(
                    label = "무",
                    homeTeamValue = 4,
                    awayTeamValue = 2
                )
                SummaryItem(
                    label = "패",
                    homeTeamValue = 6,
                    awayTeamValue = 2,
                    comparator = Comparator.reverseOrder()
                )
                SummaryItem(
                    label = "득점",
                    homeTeamValue = 30,
                    awayTeamValue = 53,
                    comparator = Comparator.naturalOrder()
                )
                SummaryItem(
                    label = "실점",
                    homeTeamValue = 27,
                    awayTeamValue = 13,
                    comparator = Comparator.reverseOrder()
                )
                SummaryItem(
                    label = "승점",
                    homeTeamValue = 31,
                    awayTeamValue = 53,
                    comparator = Comparator.naturalOrder()
                )
            }
        }
    }
}

@Composable
private fun SummaryItem(
    label: String,
    homeTeamValue: Int,
    awayTeamValue: Int,
    modifier: Modifier = Modifier,
    comparator: Comparator<Int> = Comparator { _, _ -> 0 },
) {
    Row(modifier = modifier) {
        Text(
            text = homeTeamValue.toString(),
            modifier = Modifier.weight(1f),
            color = if (comparator.compare(homeTeamValue, awayTeamValue) > 0) {
                MacaoTheme.extendedColors.success
            } else {
                MacaoTheme.colors.onSurface
            },
            textAlign = TextAlign.End,
            style = MacaoTheme.typography.subtitle2
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.width(84.dp),
                textAlign = TextAlign.Center,
                style = MacaoTheme.typography.body2
            )
        }
        Text(
            text = awayTeamValue.toString(),
            modifier = Modifier.weight(1f),
            color = if (comparator.compare(awayTeamValue, homeTeamValue) > 0) {
                MacaoTheme.extendedColors.success
            } else {
                MacaoTheme.colors.onSurface
            },
            style = MacaoTheme.typography.subtitle2
        )
    }
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

@Preview
@Composable
private fun MatchDetailsScreenPreview() {
    MacaoTheme {
        MatchDetailsScreen(matchDetails = matchDetails)
    }
}

@Preview
@Composable
private fun RelativeMatchHistoryListPreview() {
    MacaoTheme {
        MatchHistoryList(histories = matchDetails.relativeMatchHistories)
    }
}

@Preview
@Composable
private fun RankingListPreview() {
    MacaoTheme {
        RankingList(ranking = matchDetails.ranking)
    }
}

private const val SUMMARY_TAB = "요약"
private const val RELATIVE_MATCH_HISTORY_TAB = "상대 전적"
private const val HOME_TEAM_MATCH_HISTORY_TAB = "홈팀 전적"
private const val AWAY_TEAM_MATCH_HISTORY_TAB = "원정팀 전적"
private const val RANKING_TAB = "순위"

private val tabs = listOf(
    SUMMARY_TAB,
    RELATIVE_MATCH_HISTORY_TAB,
    HOME_TEAM_MATCH_HISTORY_TAB,
    AWAY_TEAM_MATCH_HISTORY_TAB,
    RANKING_TAB,
)