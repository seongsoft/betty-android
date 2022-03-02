package io.github.cbinarycastle.betty.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.match.details.matchDetails
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.entity.MatchDetails
import io.github.cbinarycastle.betty.entity.Team
import io.github.cbinarycastle.betty.ui.BettyAppBar
import io.github.cbinarycastle.betty.ui.match.LastOutcomes
import io.github.cbinarycastle.betty.ui.match.ScorePrediction
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

@Composable
fun MatchDetailsScreen(
    viewModel: MatchDetailsViewModel,
    onNavigateUp: () -> Unit,
) {
    val result by viewModel.matchDetails.collectAsState(Result.Loading)
    MatchDetailsScreen(
        matchDetailsResult = result,
        onNavigateUp = onNavigateUp,
        onTabSelected = { viewModel.onTabSelected(it.name) }
    )
}

@Composable
private fun MatchDetailsScreen(
    matchDetailsResult: Result<MatchDetails>,
    onNavigateUp: () -> Unit,
    onTabSelected: (MatchDetailsTab) -> Unit,
) {
    if (matchDetailsResult is Result.Success) {
        val matchDetails = matchDetailsResult.data
        MatchDetails(
            matchDetails = matchDetails,
            onNavigateUp = onNavigateUp,
            onTabSelected = onTabSelected,
        )
    } else {
        Scaffold(
            topBar = {
                BettyAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = onNavigateUp) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Up"
                            )
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                if (matchDetailsResult is Result.Error) {
                    Text(stringResource(R.string.match_details_error))
                } else if (matchDetailsResult == Result.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun MatchDetails(
    matchDetails: MatchDetails,
    onNavigateUp: () -> Unit,
    onTabSelected: (MatchDetailsTab) -> Unit,
    timeZoneId: ZoneId = ZoneId.systemDefault(),
    scaffoldState: CollapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = scaffoldState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            MatchDetailsCollapsingToolbar(
                scaffoldState = scaffoldState,
                onNavigateUp = onNavigateUp,
                matchDetails = matchDetails,
                timeZoneId = timeZoneId,
            )
        },
    ) {
        Column {
            MatchDetailsTabRow(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                    onTabSelected(tabs[index])
                },
            )
            when (tabs[selectedTabIndex]) {
                MatchDetailsTab.OVERVIEW -> MatchOverview(
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

@Composable
private fun MatchDetailsCollapsingToolbar(
    scaffoldState: CollapsingToolbarScaffoldState,
    onNavigateUp: () -> Unit,
    matchDetails: MatchDetails,
    timeZoneId: ZoneId,
) {
    MatchDetailsAppBar(
        title = "${matchDetails.homeTeam.displayName} vs ${matchDetails.awayTeam.displayName}",
        titleAlpha = 1f - scaffoldState.toolbarState.progress,
        onNavigateUp = onNavigateUp,
    )

    Column(
        modifier = Modifier
            .padding(top = 56.dp)
            .alpha(scaffoldState.toolbarState.progress),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
                .withZoneSameInstant(timeZoneId)
                .format(
                    DateTimeFormatter.ofLocalizedDateTime(
                        FormatStyle.MEDIUM, FormatStyle.SHORT
                    )
                ),
            style = BettyTheme.typography.subtitle1
        )
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.TopCenter) {
            with(matchDetails.suggestionInfo) {
                MatchPredictionText(
                    homePercentage = homeExpectedPercentage,
                    drawPercentage = drawExpectedPercentage,
                    awayPercentage = awayExpectedPercentage,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Team(
                    team = matchDetails.homeTeam,
                    isHome = true,
                    modifier = Modifier.weight(1f),
                )
                ScorePrediction(
                    homeScore = matchDetails.suggestionInfo.homeExpectedScore,
                    awayScore = matchDetails.suggestionInfo.awayExpectedScore,
                    textStyle = BettyTheme.typography.h5,
                )
                Team(
                    team = matchDetails.awayTeam,
                    isHome = false,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun MatchPredictionText(
    homePercentage: Int,
    drawPercentage: Int,
    awayPercentage: Int,
    modifier: Modifier = Modifier,
) {
    val homeColor = when {
        homePercentage > awayPercentage -> BettyTheme.extendedColors.win
        homePercentage < awayPercentage -> BettyTheme.extendedColors.lose
        else -> BettyTheme.extendedColors.draw
    }
    val awayColor = when {
        awayPercentage > homePercentage -> BettyTheme.extendedColors.win
        awayPercentage < homePercentage -> BettyTheme.extendedColors.lose
        else -> BettyTheme.extendedColors.draw
    }

    Row(modifier) {
        Text(
            text = "$homePercentage%",
            color = homeColor,
            style = BettyTheme.typography.caption,
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "$drawPercentage%",
            color = BettyTheme.extendedColors.draw,
            style = BettyTheme.typography.caption,
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "$awayPercentage%",
            color = awayColor,
            style = BettyTheme.typography.caption,
        )
    }
}

@Composable
private fun MatchDetailsTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        backgroundColor = BettyTheme.colors.surface,
        edgePadding = 0.dp,
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
    isHome: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = if (isHome) {
                BettyTheme.colors.primary
            } else {
                BettyTheme.colors.secondary
            },
        ) {
            Box(Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = if (isHome) {
                        stringResource(R.string.match_details_home_chip)
                    } else {
                        stringResource(R.string.match_details_away_chip)
                    },
                    style = BettyTheme.typography.subtitle2
                )
            }
        }
        Spacer(Modifier.height(8.dp))
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
        LastOutcomes(team.lastOutcomes)
    }
}

@Preview
@Composable
private fun MatchDetailsScreenPreview() {
    BettyTheme {
        MatchDetails(
            matchDetails = matchDetails,
            onNavigateUp = {},
            onTabSelected = {},
            timeZoneId = ZoneOffset.UTC,
        )
    }
}

private val tabs = MatchDetailsTab.values()