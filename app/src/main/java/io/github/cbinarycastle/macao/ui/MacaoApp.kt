package io.github.cbinarycastle.macao.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun MacaoApp() {
    MacaoTheme {
        Scaffold(
            topBar = { TopBar() }
        ) {
            MacaoNavGraph()
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) }
    )
}