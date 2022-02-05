package io.github.cbinarycastle.betty.ui

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
import io.github.cbinarycastle.betty.ui.match.details.MatchDetailsScreen
import io.github.cbinarycastle.betty.ui.match.details.MatchDetailsViewModel
import io.github.cbinarycastle.betty.ui.match.list.MatchesScreen
import io.github.cbinarycastle.betty.ui.match.list.MatchesViewModel

private const val MATCH_DETAILS_ID_KEY = "matchId"

object MainDestinations {
    const val Matches = "matches"
    const val MatchDetails = "match"
    const val StartDestination = Matches
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
        composable(MainDestinations.Matches) {
            val viewModel = hiltViewModel<MatchesViewModel>()
            MatchesScreen(
                viewModel = viewModel,
                onSelectMatch = { matchOverall ->
                    viewModel.selectMatch(matchOverall)
                    actions.openMatch(matchOverall.id)
                },
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

            MatchDetailsScreen(viewModel)
        }
    }
}

class MainActions(navController: NavHostController) {
    val openMatch = { matchId: Long ->
        navController.navigate("${MainDestinations.MatchDetails}/$matchId")
    }
}