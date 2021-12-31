package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.domain.Result
import io.github.cbinarycastle.macao.entity.MatchDetails
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun MatchDetailsScreen(viewModel: MatchDetailsViewModel) {
    val result by viewModel.matchDetails.collectAsState(Result.Loading)
    when (result) {
        Result.Loading -> CircularProgressIndicator()
        is Result.Error -> {}
        is Result.Success -> {
            val matchDetails = (result as Result.Success).data
            MatchDetailsScreen(matchDetails)
        }
    }
}

@Composable
private fun MatchDetailsScreen(matchDetails: MatchDetails) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Team(teamInfo = matchDetails.homeTeamInfo)
            Team(teamInfo = matchDetails.awayTeamInfo)
        }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MacaoTheme.colors.surface,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.size(56.dp)
                ) {
                    Text(tab)
                }
            }
        }
    }
}

private val tabs = listOf(
    "추천",
    "상대 전적",
    "홈팀 전적",
    "원정팀 전적",
    "순위",
)