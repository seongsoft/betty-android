package io.github.cbinarycastle.macao.ui.match

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.matchOveralls
import io.github.cbinarycastle.macao.entity.League
import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.entity.Team
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

private val matchTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel,
    onSelectMatch: (matchId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = viewModel.matchOveralls.collectAsLazyPagingItems()

    Column(modifier) {
        LeagueFilter(
            leagues = listOf(
                "All",
                "Premier League",
                "Bundesliga",
                "LaLiga",
                "Serie A",
            ),
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
        Divider()
        MatchOverallList(
            items = items,
            onSelectMatch = onSelectMatch,
        )
    }
}

@Composable
private fun MatchOverallList(
    items: LazyPagingItems<MatchOverallModel>,
    onSelectMatch: (matchId: Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
    }
}

@Composable
private fun MatchOverallSeparator(matchAt: LocalDateTime) {
    Spacer(Modifier.height(16.dp))
    Text(
        text = matchAt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MacaoTheme.typography.subtitle2
    )
}

@Composable
private fun MatchOverallItem(
    matchOverall: MatchOverall,
    onSelectMatch: (matchId: Long) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable { onSelectMatch(matchOverall.id) },
        elevation = 4.dp,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MacaoTheme.colors.background)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = matchOverall.matchAt.format(matchTimeFormatter),
                    style = MacaoTheme.typography.body2
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
    league: League,
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
            style = MacaoTheme.typography.caption
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
        homePercentage > awayPercentage -> MacaoTheme.extendedColors.win
        homePercentage < awayPercentage -> MacaoTheme.extendedColors.lose
        else -> MacaoTheme.extendedColors.draw
    }
    val awayColor = when {
        awayPercentage > homePercentage -> MacaoTheme.extendedColors.win
        awayPercentage < homePercentage -> MacaoTheme.extendedColors.lose
        else -> MacaoTheme.extendedColors.draw
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
                color = MacaoTheme.extendedColors.draw,
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
        homePercentage > awayPercentage -> MacaoTheme.extendedColors.win
        homePercentage < awayPercentage -> MacaoTheme.extendedColors.lose
        else -> MacaoTheme.extendedColors.draw
    }
    val awayColor = when {
        awayPercentage > homePercentage -> MacaoTheme.extendedColors.win
        awayPercentage < homePercentage -> MacaoTheme.extendedColors.lose
        else -> MacaoTheme.extendedColors.draw
    }

    Row(modifier) {
        Text(
            text = "$homePercentage%",
            color = homeColor,
            style = MacaoTheme.typography.caption,
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "$drawPercentage%",
            color = MacaoTheme.extendedColors.draw,
            style = MacaoTheme.typography.caption,
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "$awayPercentage%",
            color = awayColor,
            style = MacaoTheme.typography.caption,
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
            modifier = Modifier.size(36.dp),
            previewPlaceholder = R.drawable.manchester_united,
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = team.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MacaoTheme.typography.subtitle2,
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

@Composable
private fun ScorePrediction(
    homeScore: Int,
    awayScore: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = homeScore.toString(),
            color = if (homeScore > awayScore) {
                MacaoTheme.extendedColors.win
            } else {
                MacaoTheme.colors.onSurface
            },
            style = MacaoTheme.typography.h6
        )
        Spacer(Modifier.width(4.dp))
        Text(":")
        Spacer(Modifier.width(4.dp))
        Text(
            text = awayScore.toString(),
            color = if (awayScore > homeScore) {
                MacaoTheme.extendedColors.win
            } else {
                MacaoTheme.colors.onSurface
            },
            style = MacaoTheme.typography.h6
        )
    }
}

@Preview
@Composable
private fun MatchOverallSeparatorPreview() {
    MacaoTheme {
        MatchOverallSeparator(LocalDateTime.of(2022, 1, 1, 21, 0))
    }
}

@Preview
@Composable
private fun MatchOverallItemPreview() {
    MacaoTheme {
        MatchOverallItem(
            matchOverall = matchOveralls[0],
            onSelectMatch = {}
        )
    }
}
