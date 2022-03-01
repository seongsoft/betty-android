package io.github.cbinarycastle.betty.ui.match.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.entity.MatchOverall

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel,
    openSearch: () -> Unit,
    onMatchSelected: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
) {
    val leagues by viewModel.leagues.collectAsState()
    val matchOverallItems = viewModel.matchOveralls.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsState(false)
    val selectedLeagueIndex by viewModel.selectedLeagueIndex.collectAsState()

    LaunchedEffect(matchOverallItems.loadState.refresh) {
        viewModel.updateMatchOverallsLoading(matchOverallItems.loadState.refresh)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MatchesAppBar(onSearchButtonClick = openSearch) }
    ) {
        Column {
            Spacer(Modifier.height(16.dp))
            when (val result = leagues) {
                is Result.Success -> LeagueFilter(
                    leagueFilters = result.data,
                    selectedIndex = selectedLeagueIndex,
                    onSelect = { viewModel.selectLeague(it) },
                )
                is Result.Error -> Text(stringResource(R.string.league_filter_error))
                Result.Loading -> LeagueFilterPlaceholder()
            }
            Spacer(Modifier.height(16.dp))
            Divider()

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { viewModel.refresh() }
            ) {
                MatchOverallList(
                    items = matchOverallItems,
                    onSelectMatch = onMatchSelected
                )
            }
        }
    }
}