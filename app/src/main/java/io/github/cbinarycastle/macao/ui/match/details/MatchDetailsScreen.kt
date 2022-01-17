package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.entity.Team
import io.github.cbinarycastle.macao.ui.match.LastOutcomes
import io.github.cbinarycastle.macao.ui.match.ScorePrediction
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

private val matchDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
    FormatStyle.MEDIUM, FormatStyle.SHORT
)

@Composable
fun MatchDetailsScreen(viewModel: MatchDetailsViewModel) {
    val result by viewModel.matchDetails.collectAsState(Result.Loading)
    MatchDetailsScreen(matchDetailsResult = result)
}

@Composable
private fun MatchDetailsScreen(matchDetailsResult: Result<MatchDetails>) {
    when (matchDetailsResult) {
        is Result.Success -> {
            val matchDetails = matchDetailsResult.data
            MatchDetailsScreen(matchDetails)
        }
        is Result.Error -> {}
        Result.Loading -> CircularProgressIndicator()
    }
}

@Composable
private fun MatchDetailsScreen(matchDetails: MatchDetails) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    var selectedTab by remember { mutableStateOf(PLACE_TAB) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = matchDetails.league.name,
            style = MacaoTheme.typography.h6
        )
        Text(
            text = matchDetails.matchAt.format(matchDateTimeFormatter),
            style = MacaoTheme.typography.subtitle1
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Team(
                team = matchDetails.homeTeam,
                modifier = Modifier.weight(1f),
            )
            ScorePrediction(
                homeScore = matchDetails.suggestionInfo.homeExpectedScore,
                awayScore = matchDetails.suggestionInfo.awayExpectedScore,
            )
            Team(
                team = matchDetails.awayTeam,
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(Modifier.height(24.dp))
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MacaoTheme.colors.surface,
            edgePadding = 0.dp,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        selectedTab = tab
                    },
                    modifier = Modifier.size(56.dp)
                ) {
                    Text(tab)
                }
            }
        }

        when (selectedTab) {
            PLACE_TAB -> PlaceList(
                totalPlace = matchDetails.totalPlace,
                homePlace = matchDetails.homePlace,
                awayPlace = matchDetails.awayPlace
            )
            HOME_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(
                teamName = matchDetails.homeTeam.name,
                histories = matchDetails.homeMatchHistories
            )
            AWAY_TEAM_MATCH_HISTORY_TAB -> MatchHistoryList(
                teamName = matchDetails.awayTeam.name,
                histories = matchDetails.awayMatchHistories
            )
            RANKING_TAB -> RankingTable(
                ranking = matchDetails.ranking,
                homeTeamName = matchDetails.homeTeam.name,
                awayTeamName = matchDetails.awayTeam.name,
            )
            UNDER_OVER_TAB -> UnderOverTable(
                underOvers = matchDetails.underOvers,
                homeTeamName = matchDetails.homeTeam.name,
                awayTeamName = matchDetails.awayTeam.name,
            )
            GOALS_PER_MATCH_TAB -> {}
        }
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
            modifier = Modifier.size(60.dp),
            previewPlaceholder = R.drawable.manchester_united,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = team.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MacaoTheme.typography.subtitle2
        )
        Spacer(Modifier.height(4.dp))
        Row {
            team.lastOutcomes.forEach {
                Spacer(Modifier.width(2.dp))
                LastOutcomes(it)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}

@Preview
@Composable
private fun MatchDetailsScreenPreview() {
    MacaoTheme {
        MatchDetailsScreen(matchDetails)
    }
}

private const val PLACE_TAB = "요약"
private const val HOME_TEAM_MATCH_HISTORY_TAB = "홈팀 전적"
private const val AWAY_TEAM_MATCH_HISTORY_TAB = "원정팀 전적"
private const val RANKING_TAB = "순위"
private const val UNDER_OVER_TAB = "언더/오버"
private const val GOALS_PER_MATCH_TAB = "경기당 골"

private val tabs = listOf(
    PLACE_TAB,
    HOME_TEAM_MATCH_HISTORY_TAB,
    AWAY_TEAM_MATCH_HISTORY_TAB,
    RANKING_TAB,
    UNDER_OVER_TAB,
    GOALS_PER_MATCH_TAB,
)