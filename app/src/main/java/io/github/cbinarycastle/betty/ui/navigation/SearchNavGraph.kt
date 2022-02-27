package io.github.cbinarycastle.betty.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import io.github.cbinarycastle.betty.entity.MatchOverall
import io.github.cbinarycastle.betty.ui.search.SearchMatchesScreen
import io.github.cbinarycastle.betty.ui.search.SearchMatchesViewModel
import io.github.cbinarycastle.betty.ui.search.SearchScreen
import io.github.cbinarycastle.betty.ui.search.SearchViewModel

private const val SEARCH_MATCHES_KEYWORD_KEY = "keyword"

object SearchDestinations {
    const val Home = "search/home"
    const val Matches = "search/matches"
    const val StartDestination = Home
}

fun NavGraphBuilder.searchGraph(
    route: String,
    navController: NavHostController,
    onMatchSelected: (MatchOverall) -> Unit,
) {
    navigation(
        startDestination = SearchDestinations.StartDestination,
        route = route,
    ) {
        composable(SearchDestinations.Home) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() },
                onSearch = { keyword ->
                    navController.popBackStack()
                    navController.navigate(
                        route = "${SearchDestinations.Matches}/$keyword"
                    )
                }
            )
        }
        composable(
            route = "${SearchDestinations.Matches}/{$SEARCH_MATCHES_KEYWORD_KEY}",
            arguments = listOf(
                navArgument(SEARCH_MATCHES_KEYWORD_KEY) { type = NavType.StringType }
            )
        ) {
            val viewModel = hiltViewModel<SearchMatchesViewModel>()
            val keyword = requireNotNull(it.arguments?.getString(SEARCH_MATCHES_KEYWORD_KEY))
            viewModel.search(keyword)

            SearchMatchesScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() },
                onMatchSelected = onMatchSelected
            )
        }
    }
}