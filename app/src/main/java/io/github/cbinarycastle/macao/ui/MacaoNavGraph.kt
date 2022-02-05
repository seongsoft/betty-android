package io.github.cbinarycastle.macao.ui

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
import io.github.cbinarycastle.macao.ui.match.details.MatchDetailsScreen
import io.github.cbinarycastle.macao.ui.match.details.MatchDetailsViewModel
import io.github.cbinarycastle.macao.ui.match.list.MatchesScreen
import io.github.cbinarycastle.macao.ui.match.list.MatchesViewModel

private const val MATCH_DETAILS_ID_KEY = "matchId"

object MainDestinations {
    const val MATCHES = "matches"
    const val MATCH_DETAILS = "match"
    const val START_DESTINATION = MATCHES
}

@Composable
fun MacaoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = MainDestinations.START_DESTINATION,
        modifier = modifier,
    ) {
        composable(MainDestinations.MATCHES) {
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
            "${MainDestinations.MATCH_DETAILS}/{$MATCH_DETAILS_ID_KEY}",
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
        navController.navigate("${MainDestinations.MATCH_DETAILS}/$matchId")
    }
}