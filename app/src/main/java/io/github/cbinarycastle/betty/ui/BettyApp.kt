package io.github.cbinarycastle.betty.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun BettyApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val topLevel = backStackEntry?.destination?.route == MainDestinations.StartDestination

    BettyTheme {
        Scaffold(
            topBar = {
                TopBar(
                    topLevel = topLevel,
                    onBack = { navController.popBackStack() },
                )
            }
        ) {
            Column {
                BettyNavGraph(
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
        modifier = modifier.height(TopBarHeight),
        backgroundColor = BettyTheme.colors.surface,
    ) {
        Box(Modifier.fillMaxWidth()) {
            if (!topLevel) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up button"
                    )
                }
            }
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    color = Color.Black,
                    style = BettyTheme.typography.h6
                )
            }
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    BettyTheme {
        TopBar(topLevel = true, onBack = {})
    }
}

val TopBarHeight = 56.dp