package io.github.cbinarycastle.betty.ui.match.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.match.overall.matchOveralls
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.entity.Team
import io.github.cbinarycastle.betty.ui.match.LastOutcome
import io.github.cbinarycastle.betty.ui.match.LastOutcomes
import io.github.cbinarycastle.betty.ui.match.ScorePrediction
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import org.threeten.bp.Year
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

@Composable
fun MatchOverallSeparator(
    matchAt: ZonedDateTime,
    modifier: Modifier = Modifier,
    timeZoneId: ZoneId = ZoneId.systemDefault(),
) {
    Spacer(Modifier.height(16.dp))
    Text(
        text = matchAt
            .withZoneSameInstant(timeZoneId)
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
        modifier = modifier,
        style = BettyTheme.typography.subtitle2
    )
}

@Composable
fun MatchOverallItem(
    matchOverall: MatchOverall,
    onSelectMatch: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
    timeZoneId: ZoneId = ZoneId.systemDefault(),
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
                        .withZoneSameInstant(timeZoneId)
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
            LastOutcomes(team.lastOutcomes)
        }
    }
}

@Preview
@Composable
private fun MatchOverallSeparatorPreview() {
    BettyTheme {
        MatchOverallSeparator(
            matchAt = Year.of(2022)
                .atMonth(1)
                .atDay(1)
                .atTime(21, 0)
                .atZone(ZoneOffset.UTC),
            timeZoneId = ZoneOffset.UTC,
        )
    }
}

@Preview
@Composable
private fun MatchOverallItemPreview() {
    BettyTheme {
        MatchOverallItem(
            matchOverall = matchOveralls[0],
            onSelectMatch = {},
            timeZoneId = ZoneOffset.UTC,
        )
    }
}