package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import io.github.cbinarycastle.macao.entity.RecommendType
import io.github.cbinarycastle.macao.entity.TeamInfo
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

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
    LazyColumn {
        items(items) { item ->
            if (item != null) {
                when (item) {
                    is MatchOverallModel.Separator -> MatchOverallSeparator(item.matchAt)
                    is MatchOverallModel.Item -> {
                        MatchOverallItem(
                            matchOverall = item.matchOverall,
                            onSelectMatch = onSelectMatch,
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
private fun MatchOverallSeparator(matchAt: LocalDateTime) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MacaoTheme.extendedColors.surfaceVariant)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = matchAt.format(DateTimeFormatter.ISO_LOCAL_DATE),
            style = MacaoTheme.typography.subtitle2
        )
    }
}

@Composable
private fun MatchOverallItem(
    matchOverall: MatchOverall,
    onSelectMatch: (matchId: String) -> Unit,
) {
    Column(
        Modifier
            .clickable { onSelectMatch(matchOverall.id) }
            .padding(16.dp)
    ) {
        Text(
            text = matchOverall.leagueName,
            style = MacaoTheme.typography.caption,
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HomeTeam(
                teamInfo = matchOverall.homeTeamInfo,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = matchOverall.matchAt.format(matchDateFormatter),
                    style = MacaoTheme.typography.subtitle1
                )
                Spacer(Modifier.height(8.dp))
                RecommendationChip(matchOverall.recommend)
            }
            Spacer(Modifier.width(16.dp))
            AwayTeam(
                teamInfo = matchOverall.awayTeamInfo,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun HomeTeam(
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
        )
    }
}

@Composable
fun AwayTeam(
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
private fun RecommendationChip(recommendType: RecommendType) {
    Surface(
        shape = CircleShape,
        color = MacaoTheme.colors.primary
    ) {
        Text(
            text = recommendType.string(),
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
            style = MacaoTheme.typography.caption,
        )
    }
}

@Composable
private fun RecommendType.string() = when (this) {
    RecommendType.HOME_WIN -> stringResource(R.string.recommendation_home_win)
    RecommendType.DRAW -> stringResource(R.string.recommendation_draw)
    RecommendType.AWAY_WIN -> stringResource(R.string.recommendation_away_win)
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

@Preview
@Composable
fun RecommendationChipPreview() {
    MacaoTheme {
        RecommendationChip(RecommendType.HOME_WIN)
    }
}
