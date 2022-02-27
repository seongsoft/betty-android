package io.github.cbinarycastle.betty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import io.github.cbinarycastle.betty.ui.match.details.MatchDetailsScreen
import io.github.cbinarycastle.betty.ui.match.details.MatchDetailsViewModel
import io.github.cbinarycastle.betty.ui.match.list.MatchesScreen
import io.github.cbinarycastle.betty.ui.match.list.MatchesViewModel
import io.github.cbinarycastle.betty.ui.navigation.SearchDestinations
import io.github.cbinarycastle.betty.ui.navigation.searchGraph
import io.github.cbinarycastle.betty.ui.search.SearchMatchesScreen
import io.github.cbinarycastle.betty.ui.search.SearchMatchesViewModel
import io.github.cbinarycastle.betty.ui.search.SearchScreen
import io.github.cbinarycastle.betty.ui.search.SearchViewModel

private const val MATCH_DETAILS_MATCH_ID_KEY = "matchId"

object MainDestinations {
    const val Matches = "matches"
    const val MatchDetails = "match"
    const val Search = "search"
    const val StartDestination = Matches
}

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = MainDestinations.StartDestination,
        modifier = modifier,
    ) {
        composable(MainDestinations.Matches) { backStackEntry ->
            val viewModel = hiltViewModel<MatchesViewModel>()
            MatchesScreen(
                viewModel = viewModel,
                openSearch = actions.openSearch,
                onMatchSelected = {
                    actions.openMatch(it.id)
                },
            )
        }
        searchGraph(
            route = MainDestinations.Search,
            navController = navController,
            onMatchSelected = { actions.openMatch(it.id) }
        )
        composable(
            route = "${MainDestinations.MatchDetails}/{$MATCH_DETAILS_MATCH_ID_KEY}",
            arguments = listOf(
                navArgument(MATCH_DETAILS_MATCH_ID_KEY) { type = NavType.LongType }
            )
        ) {
            val viewModel = hiltViewModel<MatchDetailsViewModel>()
            val matchId = requireNotNull(it.arguments?.getLong(MATCH_DETAILS_MATCH_ID_KEY))
            viewModel.setMatchId(matchId)

            MatchDetailsScreen(
                viewModel = viewModel,
                onNavigateUp = actions.navigateUp
            )
        }
    }
}

class MainActions(navController: NavHostController) {

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val openSearch = {
        navController.navigate(MainDestinations.Search)
    }

    val search: (String) -> Unit = { keyword ->
        navController.popBackStack()
        navController.navigate(
            route = "${SearchDestinations.Matches}/$keyword"
        )
    }

    val openMatch = { matchId: Long ->
        navController.navigate("${MainDestinations.MatchDetails}/$matchId")
    }
}