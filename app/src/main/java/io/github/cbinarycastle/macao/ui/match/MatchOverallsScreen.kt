package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.matchOveralls
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.TeamInfo
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import io.github.cbinarycastle.macao.ui.theme.blueGray600
import io.github.cbinarycastle.macao.ui.theme.green800
import io.github.cbinarycastle.macao.ui.theme.red700
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

private val matchDateFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)

@Composable
fun MatchOverallsScreen(viewModel: MatchOverallsViewModel) {
    val result by viewModel.matchOveralls.collectAsState()

    if (result is Result.Success) {
        MatchOverallList(matchOveralls = (result as Result.Success).data)
    } else {
        Row {
            CircularProgressIndicator(Modifier.wrapContentSize())
        }
    }
}

@Composable
private fun MatchOverallList(matchOveralls: List<MatchOverall>) {
    LazyColumn {
        matchOveralls.forEach { matchOverall ->
            item {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                        .clickable { }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = matchOverall.leagueName,
                            style = MacaoTheme.typography.caption,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = matchOverall.matchAt.format(matchDateFormatter),
                            style = MacaoTheme.typography.caption,
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Team(matchOverall.homeTeamInfo)
                            Text(
                                text = "vs.",
                                style = MacaoTheme.typography.subtitle1
                            )
                            Team(matchOverall.awayTeamInfo)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Team(teamInfo: TeamInfo) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // TODO: Image로 교체
        Surface(
            modifier = Modifier.size(60.dp),
            color = Color.LightGray,
            shape = CircleShape
        ) {}
        Spacer(Modifier.height(4.dp))
        Text(
            text = teamInfo.teamName,
            style = MacaoTheme.typography.subtitle2
        )
        Spacer(Modifier.height(4.dp))
        Row {
            teamInfo.recentRecords.forEach {
                Spacer(Modifier.width(2.dp))
                OutComeText(it)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}

@Composable
private fun OutComeText(outCome: OutCome) {
    Text(
        modifier = Modifier.wrapContentSize(),
        text = outCome.stringResource(),
        color = outCome.textColor(),
        style = MacaoTheme.typography.caption ,
    )
}

@Composable
fun OutCome.stringResource() = stringResource(
    when (this) {
        OutCome.WIN -> R.string.win
        OutCome.DRAW -> R.string.draw
        OutCome.LOSE -> R.string.lose
    }
)

@Composable
fun OutCome.textColor() = when (this) {
    OutCome.WIN -> green800
    OutCome.DRAW -> blueGray600
    OutCome.LOSE -> red700
}

@Preview
@Composable
fun MatchOverallListPreview() {
    MatchOverallList(matchOveralls = matchOveralls)
}
