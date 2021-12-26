package io.github.cbinarycastle.macao.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.cbinarycastle.macao.ui.match.MatchDetails
import io.github.cbinarycastle.macao.ui.match.MatchOverallsScreen
import io.github.cbinarycastle.macao.ui.match.MatchOverallsViewModel

object MainDestinations {
    const val MATCH_OVERALLS = "matchOveralls"
    const val MATCH_DETAILS = "matchDetails"
}

@Composable
fun MacaoNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.MATCH_OVERALLS,
    ) {
        composable(MainDestinations.MATCH_OVERALLS) {
            val viewModel = hiltViewModel<MatchOverallsViewModel>()
            MatchOverallsScreen(viewModel)
        }
        composable(MainDestinations.MATCH_DETAILS) {
            MatchDetails()
        }
    }
}