package io.github.cbinarycastle.macao.ui.match

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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

private val matchDateFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel,
    onSelectMatch: (matchId: Long) -> Unit,
) {
    val items = viewModel.matchOveralls.collectAsLazyPagingItems()

    Column {
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
            League(matchOverall.league)
            MatchPrediction(
                homePercentage = matchOverall.suggestionInfo.homeExpectedPercentage,
                drawPercentage = matchOverall.suggestionInfo.drawExpectedPercentage,
                awayPercentage = matchOverall.suggestionInfo.awayExpectedPercentage
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Team(
                    team = matchOverall.homeTeam,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MatchTime(matchOverall.matchAt)
                    Spacer(Modifier.height(8.dp))
                    Row {
                        Text(matchOverall.suggestionInfo.homeExpectedScore.toString())
                        Spacer(Modifier.width(4.dp))
                        Text(":")
                        Spacer(Modifier.width(4.dp))
                        Text(matchOverall.suggestionInfo.awayExpectedScore.toString())
                    }
                }
                Spacer(Modifier.width(16.dp))
                Team(
                    team = matchOverall.awayTeam,
                    modifier = Modifier.weight(1f)
                )
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
        modifier
            .fillMaxWidth()
            .background(MacaoTheme.colors.background)
            .padding(8.dp),
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
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        RecentRecordStatus(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun MatchTime(matchAt: LocalDateTime) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MacaoTheme.colors.onSurface.copy(alpha = 0.12f),
        )
    ) {
        Text(
            text = matchAt.format(matchDateFormatter),
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
            style = MacaoTheme.typography.subtitle2
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MatchPrediction(
    homePercentage: Int,
    drawPercentage: Int,
    awayPercentage: Int,
) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    val homeColor = when {
        homePercentage > awayPercentage -> MacaoTheme.extendedColors.success
        homePercentage < awayPercentage -> MacaoTheme.colors.error
        else -> MacaoTheme.extendedColors.neutral
    }
    val awayColor = when {
        awayPercentage > homePercentage -> MacaoTheme.extendedColors.success
        awayPercentage < homePercentage -> MacaoTheme.colors.error
        else -> MacaoTheme.extendedColors.neutral
    }

    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.animateEnterExit(enter = slideInHorizontally())) {
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
                    color = MacaoTheme.extendedColors.neutral,
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
            Spacer(Modifier.height(4.dp))
            Row {
                Text(
                    text = "$homePercentage%",
                    color = homeColor,
                    style = MacaoTheme.typography.caption,
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "$drawPercentage%",
                    color = MacaoTheme.extendedColors.neutral,
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
    }
}

@Preview
@Composable
fun MatchOverallSeparatorPreview() {
    MacaoTheme {
        MatchOverallSeparator(LocalDateTime.of(2022, 1, 1, 21, 0))
    }
}

@Preview
@Composable
fun MatchOverallItemPreview() {
    MacaoTheme {
        MatchOverallItem(
            matchOverall = matchOveralls[0],
            onSelectMatch = {}
        )
    }
}
