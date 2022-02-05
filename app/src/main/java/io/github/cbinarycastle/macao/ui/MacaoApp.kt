package io.github.cbinarycastle.macao.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import io.github.cbinarycastle.macao.BuildConfig
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
                TopBar(
                    topLevel = topLevel,
                    onBack = { navController.popBackStack() },
                )
            }
        ) {
            Column {
                MacaoNavGraph(
                    modifier = Modifier.weight(1f),
                    navController = navController
                )
                AndroidView(
                    factory = { context ->
                        AdView(context).apply {
                            adSize = AdSize.BANNER
                            adUnitId = BuildConfig.ADMOB_AD_UNIT_ID
                            loadAd(AdRequest.Builder().build())
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    topLevel: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier.height(TopBarHeight),
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
    MacaoTheme {
        TopBar(topLevel = true, onBack = {})
    }
}

val TopBarHeight = 56.dp