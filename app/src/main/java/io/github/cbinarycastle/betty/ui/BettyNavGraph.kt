package io.github.cbinarycastle.betty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.cbinarycastle.betty.ui.match.details.MatchDetailsScreen
import io.github.cbinarycastle.betty.ui.match.details.MatchDetailsViewModel
import io.github.cbinarycastle.betty.ui.match.list.MatchesScreen
import io.github.cbinarycastle.betty.ui.match.list.MatchesViewModel
import io.github.cbinarycastle.betty.ui.search.SearchScreen
import io.github.cbinarycastle.betty.ui.search.SearchViewModel

private const val MATCH_DETAILS_ID_KEY = "matchId"

object MainDestinations {
    const val Matches = "matches"
    const val Search = "search"
    const val MatchDetails = "match"
    const val StartDestination = Matches
}

object SavedStateHandleData {
    const val SearchKeyword = "searchKeyword"
}

@Composable
fun BettyNavGraph(
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

            backStackEntry.savedStateHandle
                .getLiveData<String>(SavedStateHandleData.SearchKeyword)
                .observe(LocalLifecycleOwner.current) { keyword ->
                    viewModel.search(keyword)
                }

            MatchesScreen(
                viewModel = viewModel,
                openSearch = actions.openSearch,
                onMatchSelected = { matchOverall ->
                    viewModel.selectMatch(matchOverall)
                    actions.openMatch(matchOverall.id)
                },
            )
        }
        composable(MainDestinations.Search) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = viewModel,
                onNavigateUp = actions.navigateUp,
                onSearch = actions.search
            )
        }
        composable(
            "${MainDestinations.MatchDetails}/{$MATCH_DETAILS_ID_KEY}",
            arguments = listOf(
                navArgument(MATCH_DETAILS_ID_KEY) { type = NavType.LongType }
            )
        ) {
            val viewModel = hiltViewModel<MatchDetailsViewModel>()
            val matchId = requireNotNull(it.arguments?.getLong(MATCH_DETAILS_ID_KEY))
            viewModel.setMatchId(matchId)

            MatchDetailsScreen(
                viewModel = viewModel,
                onNavigateUp = actions.navigateUp
            )
        }
    }
}

class MainActions(navController: NavHostController) {

    val openSearch = {
        navController.navigate(MainDestinations.Search)
    }

    val openMatch = { matchId: Long ->
        navController.navigate("${MainDestinations.MatchDetails}/$matchId")
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val search: (String) -> Unit = { keyword ->
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set(SavedStateHandleData.SearchKeyword, keyword)
        navController.navigateUp()
    }
}