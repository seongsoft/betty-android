package io.github.cbinarycastle.betty.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.match.details.matchDetails
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.entity.MatchDetails
import io.github.cbinarycastle.betty.entity.Team
import io.github.cbinarycastle.betty.ui.components.CollapsibleLayout
import io.github.cbinarycastle.betty.ui.components.rememberCollapsibleState
import io.github.cbinarycastle.betty.ui.match.LastOutcomes
import io.github.cbinarycastle.betty.ui.match.ScorePrediction
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import kotlinx.coroutines.launch
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

@Composable
fun MatchDetailsScreen(viewModel: MatchDetailsViewModel) {
    val result by viewModel.matchDetails.collectAsState(Result.Loading)
    MatchDetailsScreen(
        matchDetailsResult = result,
        onTabSelected = { viewModel.onTabSelected(it.name) }
    )
}

@Composable
private fun MatchDetailsScreen(
    matchDetailsResult: Result<MatchDetails>,
    onTabSelected: (MatchDetailsTab) -> Unit,
) {
    when (matchDetailsResult) {
        is Result.Success -> {
            val matchDetails = matchDetailsResult.data
            MatchDetailsScreen(
                matchDetails = matchDetails,
                onTabSelected = onTabSelected,
            )
        }
        is Result.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(stringResource(R.string.match_details_error))
            }
        }
        Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MatchDetailsScreen(
    matchDetails: MatchDetails,
    onTabSelected: (MatchDetailsTab) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    CollapsibleLayout(
        state = rememberCollapsibleState(),
        collapsible = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GlideImage(
                        imageModel = matchDetails.league.imageUrl,
                        modifier = Modifier.size(24.dp),
                        previewPlaceholder = R.drawable.premier_league,
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = matchDetails.league.name,
                        style = BettyTheme.typography.h6
                    )
                }
                Text(
                    text = matchDetails.matchAt
                        .withZoneSameInstant(ZoneOffset.systemDefault())
                        .format(
                            DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM, FormatStyle.SHORT
                            )
                        ),
                    style = BettyTheme.typography.subtitle1
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Team(
                        team = matchDetails.homeTeam,
                        modifier = Modifier.weight(1f),
                    )
                    ScorePrediction(
                        homeScore = matchDetails.suggestionInfo.homeExpectedScore,
                        awayScore = matchDetails.suggestionInfo.awayExpectedScore,
                    )
                    Team(
                        team = matchDetails.awayTeam,
                        modifier = Modifier.weight(1f),
                    )
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    ) {
        Column {
            MatchDetailsTabRow(
                selectedTabIndex = pagerState.currentPage,
                pagerState = pagerState,
                onTabSelected = { index ->
                    onTabSelected(tabs[index])
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
            HorizontalPager(
                count = tabs.size,
                state = pagerState,
            ) { page ->
                when (tabs[page]) {
                    MatchDetailsTab.PLACE -> PlaceList(
                        totalPlace = matchDetails.totalPlace,
                        homePlace = matchDetails.homePlace,
                        awayPlace = matchDetails.awayPlace
                    )
                    MatchDetailsTab.HOME_TEAM_MATCH_HISTORY -> MatchHistoryList(
                        teamName = matchDetails.homeTeam.originalName,
                        histories = matchDetails.homeMatchHistories
                    )
                    MatchDetailsTab.AWAY_TEAM_MATCH_HISTORY -> MatchHistoryList(
                        teamName = matchDetails.awayTeam.originalName,
                        histories = matchDetails.awayMatchHistories
                    )
                    MatchDetailsTab.RANKING -> RankingTable(
                        ranking = matchDetails.ranking,
                        homeTeamName = matchDetails.homeTeam.originalName,
                        awayTeamName = matchDetails.awayTeam.originalName,
                    )
                    MatchDetailsTab.UNDER_OVER -> UnderOverTable(
                        underOvers = matchDetails.underOvers,
                        homeTeamName = matchDetails.homeTeam.originalName,
                        awayTeamName = matchDetails.awayTeam.originalName,
                    )
                    MatchDetailsTab.GOALS_PER_MATCH -> GoalsPerMatchTable(
                        goalsPerMatches = matchDetails.goalsPerMatches,
                        homeTeamName = matchDetails.homeTeam.originalName,
                        awayTeamName = matchDetails.awayTeam.originalName,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MatchDetailsTabRow(
    selectedTabIndex: Int,
    pagerState: PagerState,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        backgroundColor = BettyTheme.colors.surface,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState = pagerState,
                    tabPositions = tabPositions
                )
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                modifier = Modifier.height(56.dp)
            ) {
                Text(
                    text = stringResource(tab.titleResId),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
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
            text = team.displayName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = BettyTheme.typography.subtitle2
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

@Preview
@Composable
private fun MatchDetailsScreenPreview() {
    BettyTheme {
        MatchDetailsScreen(
            matchDetails = matchDetails,
            onTabSelected = {}
        )
    }
}

private val tabs = MatchDetailsTab.values()