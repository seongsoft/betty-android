package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.matchOveralls
import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.entity.TeamInfo
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

private val matchDateFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun MatchOverallsScreen(
    viewModel: MatchOverallsViewModel,
    onSelectMatch: (matchId: String) -> Unit,
) {
    val items = viewModel.matchOveralls.collectAsLazyPagingItems()
    MatchOverallList(
        items = items,
        onSelectMatch = onSelectMatch,
    )
}

@Composable
private fun MatchOverallList(
    items: LazyPagingItems<MatchOverallModel>,
    onSelectMatch: (matchId: String) -> Unit,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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
        color = MacaoTheme.colors.primary,
        style = MacaoTheme.typography.h6
    )
}

@Composable
private fun MatchOverallItem(
    matchOverall: MatchOverall,
    onSelectMatch: (matchId: String) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = 4.dp,
    ) {
        Column {
            Column(
                Modifier
                    .clickable { onSelectMatch(matchOverall.id) }
                    .padding(16.dp)
            ) {
                LeagueName(matchOverall.leagueName)
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Team(
                        teamInfo = matchOverall.homeTeamInfo,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(16.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        MatchTime(matchOverall.matchAt)
                        Spacer(Modifier.height(8.dp))
                        PredictionPercentageText()
                    }
                    Spacer(Modifier.width(16.dp))
                    Team(
                        teamInfo = matchOverall.awayTeamInfo,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            PredictionPercentageBar()
        }
    }
}

@Composable
private fun LeagueName(leagueName: String) {
    Text(
        text = leagueName,
        style = MacaoTheme.typography.caption,
    )
}

@Composable
private fun HomeTeam(
    teamInfo: TeamInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = teamInfo.teamName,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MacaoTheme.typography.subtitle2,
            )
            Spacer(Modifier.height(4.dp))
            Row {
                teamInfo.recentRecords.forEach {
                    Spacer(Modifier.width(4.dp))
                    RecentRecordStatus(it)
                }
            }
        }
        Spacer(Modifier.width(8.dp))
        GlideImage(
            imageModel = teamInfo.logoUrl,
            modifier = Modifier.size(36.dp),
            previewPlaceholder = R.drawable.manchester_united,
        )
    }
}

@Composable
private fun MatchTime(matchAt: LocalDateTime) {
    Text(
        text = matchAt.format(matchDateFormatter),
        style = MacaoTheme.typography.h6
    )
}

@Composable
private fun PredictionPercentageText() {
    Row {
        Text(
            text = "34%",
            color = MacaoTheme.colors.error,
            style = MacaoTheme.typography.caption,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "20%",
            color = MacaoTheme.extendedColors.neutral,
            style = MacaoTheme.typography.caption,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "46%",
            color = MacaoTheme.extendedColors.success,
            style = MacaoTheme.typography.caption,
        )
    }
}

@Composable
private fun AwayTeam(
    teamInfo: TeamInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            imageModel = teamInfo.logoUrl,
            modifier = Modifier.size(36.dp),
            previewPlaceholder = R.drawable.manchester_city,
        )
        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = teamInfo.teamName,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MacaoTheme.typography.subtitle2,
            )
            Spacer(Modifier.height(4.dp))
            Row {
                teamInfo.recentRecords.forEach {
                    RecentRecordStatus(it)
                    Spacer(Modifier.width(4.dp))
                }
            }
        }
    }
}

@Composable
private fun PredictionPercentageBar() {
    Row {
        Surface(
            modifier = Modifier
                .weight(34f)
                .height(4.dp),
            color = MacaoTheme.colors.error,
            content = {}
        )
        Surface(
            modifier = Modifier
                .weight(20f)
                .height(4.dp),
            color = MacaoTheme.extendedColors.neutral,
            content = {}
        )
        Surface(
            modifier = Modifier
                .weight(46f)
                .height(4.dp),
            color = MacaoTheme.extendedColors.success,
            content = {}
        )
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
