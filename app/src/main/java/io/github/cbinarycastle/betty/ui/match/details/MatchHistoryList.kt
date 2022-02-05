package io.github.cbinarycastle.betty.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.betty.data.match.details.matchDetails
import io.github.cbinarycastle.betty.entity.MatchHistory
import io.github.cbinarycastle.betty.ui.match.color
import io.github.cbinarycastle.betty.ui.match.text
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun MatchHistoryList(
    teamName: String,
    histories: List<MatchHistory>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(histories) {
            MatchHistoryItem(teamName, it)
        }
    }
}

@Composable
private fun MatchHistoryItem(teamName: String, history: MatchHistory) {
    Row(
        Modifier
            .padding(vertical = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = history.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    style = BettyTheme.typography.caption,
                )
            }
            Row(
                modifier = Modifier.padding(start = 112.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Highlight(enabled = teamName == history.homeTeamName) {
                        Text(
                            text = history.homeTeamName,
                            style = BettyTheme.typography.subtitle2,
                        )
                    }
                    Highlight(enabled = teamName == history.awayTeamName) {
                        Text(
                            text = history.awayTeamName,
                            style = BettyTheme.typography.subtitle2,
                        )
                    }
                }
                Box(contentAlignment = Alignment.CenterEnd) {
                    Box(Modifier.padding(end = 24.dp)) {
                        Surface(
                            modifier = Modifier.size(24.dp),
                            shape = CircleShape,
                            color = history.outcome.color(),
                            contentColor = Color.White
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = history.outcome.text(),
                                    style = BettyTheme.typography.caption
                                )
                            }
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Highlight(enabled = teamName == history.homeTeamName) {
                            Text(
                                text = history.homeScore.toString(),
                                style = BettyTheme.typography.subtitle2,
                            )
                        }
                        Highlight(enabled = teamName == history.awayTeamName) {
                            Text(
                                text = history.awayScore.toString(),
                                style = BettyTheme.typography.subtitle2,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Highlight(
    enabled: Boolean,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalContentAlpha provides if (enabled) {
            ContentAlpha.high
        } else {
            ContentAlpha.disabled
        }
    ) {
        content()
    }
}

@Composable
private fun MatchHistoryTeam(
    teamName: String,
    score: Int,
    shouldHighlight: Boolean,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalContentAlpha provides if (shouldHighlight) {
            ContentAlpha.high
        } else {
            ContentAlpha.disabled
        }
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = teamName,
                style = BettyTheme.typography.subtitle2,
            )
            Text(
                text = score.toString(),
                style = BettyTheme.typography.subtitle2,
            )
        }
    }
}

@Preview
@Composable
private fun MatchHistoryListPreview() {
    BettyTheme {
        MatchHistoryList(
            teamName = matchDetails.homeTeam.displayName,
            histories = matchDetails.homeMatchHistories
        )
    }
}