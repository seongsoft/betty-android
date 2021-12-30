package io.github.cbinarycastle.macao.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun MacaoApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val topLevel = backStackEntry?.destination?.route == MainDestinations.START_DESTINATION

    MacaoTheme {
        Scaffold(
            topBar = {
                TopBar(topLevel = topLevel) { navController.popBackStack() }
            }
        ) {
            MacaoNavGraph(navController)
        }
    }
}

@Composable
private fun TopBar(
    topLevel: Boolean,
    onBack: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MacaoTheme.colors.surface,
        navigationIcon = if (topLevel) {
            null
        } else {
            {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up button"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(true) {}
}