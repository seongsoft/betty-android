package io.github.cbinarycastle.betty.ui.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.ui.match.list.MatchOverallList

@Composable
fun SearchMatchesScreen(
    viewModel: SearchMatchesViewModel,
    onNavigateUp: () -> Unit,
    onMatchSelected: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyword by viewModel.keyword.collectAsState()
    val matchOverallItems = viewModel.matchOveralls.collectAsLazyPagingItems()
    val isRefreshing = remember(matchOverallItems.loadState.refresh) {
        matchOverallItems.loadState.refresh is LoadState.Loading
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchMatchesAppBar(
                title = keyword ?: "",
                onNavigateUp = onNavigateUp
            )
        }
    ) {
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