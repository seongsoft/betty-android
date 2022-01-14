package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.entity.MatchHistory
import io.github.cbinarycastle.macao.ui.match.color
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun MatchHistoryList(teamName: String, histories: List<MatchHistory>) {
    Column {
        histories.forEach { MatchHistoryItem(teamName, it) }
    }
}

@Composable
private fun MatchHistoryItem(teamName: String, history: MatchHistory) {
    Row(
        Modifier
            .padding(vertical = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Surface(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight(),
            color = history.outcome.color(),
            content = {}
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = history.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
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
                MatchHistoryTeam(
                    teamName = history.homeTeamName,
                    score = history.homeScore,
                    shouldHighlight = teamName == history.homeTeamName
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MatchHistoryTeam(
                    teamName = history.awayTeamName,
                    score = history.awayScore,
                    shouldHighlight = teamName == history.awayTeamName
                )
            }
        }
    }
}

@Composable
private fun MatchHistoryTeam(
    teamName: String,
    score: Int,
    shouldHighlight: Boolean,
) {
    CompositionLocalProvider(
        LocalContentAlpha provides if (shouldHighlight) {
            ContentAlpha.high
        } else {
            ContentAlpha.disabled
        }
    ) {
        Text(
            text = teamName,
            style = MacaoTheme.typography.subtitle2,
        )
        Text(
            text = score.toString(),
            style = MacaoTheme.typography.subtitle2,
        )
    }
}

@Preview
@Composable
private fun MatchHistoryListPreview() {
    MacaoTheme {
        MatchHistoryList(
            teamName = matchDetails.homeTeam.name,
            histories = matchDetails.homeMatchHistories
        )
    }
}