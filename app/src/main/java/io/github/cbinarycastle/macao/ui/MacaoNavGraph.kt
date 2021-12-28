package io.github.cbinarycastle.macao.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.cbinarycastle.macao.ui.match.MatchDetailsScreen
import io.github.cbinarycastle.macao.ui.match.MatchOverallsScreen
import io.github.cbinarycastle.macao.ui.match.MatchOverallsViewModel

private const val MATCH_DETAILS_ID_KEY = "matchId"

object MainDestinations {
    const val MATCH_OVERALLS = "matches"
    const val MATCH_DETAILS = "match"
}

@Composable
fun MacaoNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = MainDestinations.MATCH_OVERALLS,
    ) {
        composable(MainDestinations.MATCH_OVERALLS) {
            val viewModel = hiltViewModel<MatchOverallsViewModel>()
            MatchOverallsScreen(
                viewModel = viewModel,
                selectMatch = actions.openMatch,
            )
        }
        composable(
            "${MainDestinations.MATCH_DETAILS}/{$MATCH_DETAILS_ID_KEY}",
            arguments = listOf(
                navArgument(MATCH_DETAILS_ID_KEY) { type = NavType.StringType }
            )
        ) {
            val matchId = requireNotNull(it.arguments?.getString(MATCH_DETAILS_ID_KEY))
            MatchDetailsScreen(matchId)
        }
    }
}

class MainActions(navController: NavHostController) {
    val openMatch = { matchId: String ->
        navController.navigate("${MainDestinations.MATCH_DETAILS}/$matchId")
    }
}