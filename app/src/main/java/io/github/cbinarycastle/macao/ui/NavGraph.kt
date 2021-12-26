package io.github.cbinarycastle.macao.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val MATCHES = "matches"
    const val MATCH_DETAILS = "matchDetails"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.MATCHES,
    ) {
        composable(MainDestinations.MATCHES) {
            Matches()
        }
        composable(MainDestinations.MATCH_DETAILS) {
            MatchDetails()
        }
    }
}