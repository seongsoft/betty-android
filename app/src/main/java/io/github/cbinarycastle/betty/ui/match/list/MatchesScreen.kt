package io.github.cbinarycastle.betty.ui.match.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.match.overall.matchOveralls
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.entity.Team
import io.github.cbinarycastle.betty.ui.match.LastOutcomes
import io.github.cbinarycastle.betty.ui.match.ScorePrediction
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import org.threeten.bp.Year
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel,
    onSelectMatch: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
) {
    val leagues by viewModel.leagues.collectAsState()
    val matchOverallItems = viewModel.matchOveralls.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsState(false)
    val selectedLeagueIndex by viewModel.selectedLeagueIndex.collectAsState()

    LaunchedEffect(matchOverallItems.loadState.refresh) {
        viewModel.updateMatchOverallsLoading(matchOverallItems.loadState.refresh)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(16.dp))
        when (val result = leagues) {
            is Result.Success -> LeagueFilter(
                leagueFilters = result.data,
                selectedIndex = selectedLeagueIndex,
                onSelect = { viewModel.selectLeague(it) },
            )
            is Result.Error -> Text(stringResource(R.string.league_filter_error))
            Result.Loading -> LeagueFilterPlaceholder()
        }
        Spacer(Modifier.height(16.dp))
        Divider()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() }
        ) {
            MatchOverallList(
                items = matchOverallItems,
                onSelectMatch = onSelectMatch
            )
        }
    }
}

@Composable
private fun MatchOverallList(
    items: LazyPagingItems<MatchOverallModel>,
    onSelectMatch: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (items.loadState.refresh) {
        is LoadState.NotLoading -> {
            if (items.itemCount > 0) {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(items) { item ->
                        if (item != null) {
                            when (item) {
                                is MatchOverallModel.Separator -> MatchOverallSeparator(item.matchAt)
                                is MatchOverallModel.Item -> {
                                    MatchOverallItem(
                                        matchOverall = item.matchOverall,
                                        onSelectMatch = onSelectMatch,
                                    )
                                }
                            }
                        }
                    }
                    if (items.loadState.append is LoadState.Loading) {
                        item {
                            Spacer(Modifier.height(8.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(stringResource(R.string.matches_no_result))
                }
            }
        }
        is LoadState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.matches_error))
                Spacer(Modifier.height(8.dp))
                Button(onClick = { items.retry() }) {
                    Text(stringResource(R.string.retry))
                }
            }
        }
        LoadState.Loading -> MatchOverallListPlaceholder()
    }
}

@Composable
private fun MatchOverallListPlaceholder() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        MatchOverallSeparator(
            matchAt = ZonedDateTime.now(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                )
        )
        repeat(4) {
            MatchOverallItem(
                matchOverall = matchOveralls[0],
                onSelectMatch = {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                    )
            )
        }
    }
}

@Composable
private fun MatchOverallSeparator(
    matchAt: ZonedDateTime,
    modifier: Modifier = Modifier,
) {
    Spacer(Modifier.height(16.dp))
    Text(
        text = matchAt
            .withZoneSameInstant(ZoneOffset.systemDefault())
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
        modifier = modifier,
        style = BettyTheme.typography.subtitle2
    )
}

@Composable
private fun MatchOverallItem(
    matchOverall: MatchOverall,
    onSelectMatch: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.clickable { onSelectMatch(matchOverall) },
        elevation = 4.dp,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = matchOverall.matchAt
                        .withZoneSameInstant(ZoneOffset.systemDefault())
                        .format(DateTimeFormatter.ofPattern("HH:mm")),
                    style = BettyTheme.typography.body2
                )
                League(matchOverall.league)
            }
            with(matchOverall.suggestionInfo) {
                MatchPredictionBar(
                    homePercentage = homeExpectedPercentage,
                    drawPercentage = drawExpectedPercentage,
                    awayPercentage = awayExpectedPercentage,
                )
            }
            Box(
                modifier = Modifier.padding(vertical = 16.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                with(matchOverall.suggestionInfo) {
                    MatchPredictionText(
                        homePercentage = homeExpectedPercentage,
                        drawPercentage = drawExpectedPercentage,
                        awayPercentage = awayExpectedPercentage,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Team(
                        team = matchOverall.homeTeam,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )
                    with(matchOverall.suggestionInfo) {
                        ScorePrediction(
                            homeScore = homeExpectedScore,
                            awayScore = awayExpectedScore,
                        )
                    }
                    Team(
                        team = matchOverall.awayTeam,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun League(
    league: MatchOverall.League,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            imageModel = league.imageUrl,
            modifier = Modifier.size(24.dp),
            previewPlaceholder = R.drawable.premier_league,
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = league.name,
            textAlign = TextAlign.Center,
            style = BettyTheme.typography.caption
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MatchPredictionBar(
    homePercentage: Int,
    drawPercentage: Int,
    awayPercentage: Int,
    modifier: Modifier = Modifier
) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
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

    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn() + slideInHorizontally(),
    ) {
        Row(modifier) {
            Surface(
                modifier = Modifier
                    .weight(homePercentage.toFloat())
                    .height(4.dp),
                color = homeColor,
                content = {}
            )
            Surface(
                modifier = Modifier
                    .weight(drawPercentage.toFloat())
                    .height(4.dp),
                color = BettyTheme.extendedColors.draw,
                content = {}
            )
            Surface(
                modifier = Modifier
                    .weight(awayPercentage.toFloat())
                    .height(4.dp),
                color = awayColor,
                content = {}
            )
        }
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
            modifier = Modifier.size(48.dp),
            previewPlaceholder = R.drawable.manchester_united,
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = team.displayName,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = BettyTheme.typography.subtitle2,
            )
            Spacer(Modifier.height(4.dp))
            Row {
                team.lastOutcomes.forEach {
                    Spacer(Modifier.width(4.dp))
                    LastOutcomes(it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun MatchOverallSeparatorPreview() {
    BettyTheme {
        MatchOverallSeparator(
            Year.of(2022)
                .atMonth(1)
                .atDay(1)
                .atTime(21, 0)
                .atZone(ZoneOffset.UTC)
        )
    }
}

@Preview
@Composable
private fun MatchOverallItemPreview() {
    BettyTheme {
        MatchOverallItem(
            matchOverall = matchOveralls[0],
            onSelectMatch = {}
        )
    }
}