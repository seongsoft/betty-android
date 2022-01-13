package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import io.github.cbinarycastle.macao.entity.Place
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
    var selectedTab by remember { mutableStateOf(PLACE_TAB) }

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
            PLACE_TAB -> PlaceList(
                totalPlace = matchDetails.totalPlace,
                homePlace = matchDetails.homePlace,
                awayPlace = matchDetails.awayPlace
            )
            HOME_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.homeMatchHistories)
            AWAY_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(matchDetails.awayMatchHistories)
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
private fun PlaceList(
    totalPlace: Place,
    homePlace: Place,
    awayPlace: Place,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PlaceCard(
            place = totalPlace,
            title = stringResource(R.string.place_total_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        PlaceCard(
            place = homePlace,
            title = stringResource(R.string.place_home_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        PlaceCard(
            place = awayPlace,
            title = stringResource(R.string.place_away_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun PlaceCard(
    place: Place,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MacaoTheme.typography.h6
            )
            Spacer(Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                PlaceRow(
                    label = "경기",
                    homeTeamValue = place.home.totalMatchCount,
                    awayTeamValue = place.away.totalMatchCount
                )
                PlaceRow(
                    label = "승",
                    homeTeamValue = place.home.winMatchCount,
                    awayTeamValue = place.away.winMatchCount,
                    comparator = Comparator.naturalOrder()
                )
                PlaceRow(
                    label = "무",
                    homeTeamValue = place.home.drawMatchCount,
                    awayTeamValue = place.away.drawMatchCount
                )
                PlaceRow(
                    label = "패",
                    homeTeamValue = place.home.loseMatchCount,
                    awayTeamValue = place.away.loseMatchCount,
                    comparator = Comparator.reverseOrder()
                )
                PlaceRow(
                    label = "득점",
                    homeTeamValue = place.home.goalFor,
                    awayTeamValue = place.away.goalFor,
                    comparator = Comparator.naturalOrder()
                )
                PlaceRow(
                    label = "실점",
                    homeTeamValue = place.home.goalAgainst,
                    awayTeamValue = place.away.goalAgainst,
                    comparator = Comparator.reverseOrder()
                )
                PlaceRow(
                    label = "승점",
                    homeTeamValue = place.home.points,
                    awayTeamValue = place.away.points,
                    comparator = Comparator.naturalOrder()
                )
            }
        }
    }
}

@Composable
private fun PlaceRow(
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
                MacaoTheme.extendedColors.win
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
                MacaoTheme.extendedColors.win
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
                text = history.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
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
                    text = history.homeScore.toString(),
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
                    text = history.awayScore.toString(),
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
private fun MatchHistoryListPreview() {
    MacaoTheme {
        MatchHistoryList(histories = matchDetails.homeMatchHistories)
    }
}

@Preview
@Composable
private fun RankingListPreview() {
    MacaoTheme {
        RankingList(ranking = matchDetails.ranking)
    }
}

private const val PLACE_TAB = "요약"
private const val HOME_TEAM_MATCH_HISTORY_TAB = "홈팀 전적"
private const val AWAY_TEAM_MATCH_HISTORY_TAB = "원정팀 전적"
private const val RANKING_TAB = "순위"

private val tabs = listOf(
    PLACE_TAB,
    HOME_TEAM_MATCH_HISTORY_TAB,
    AWAY_TEAM_MATCH_HISTORY_TAB,
    RANKING_TAB,
)