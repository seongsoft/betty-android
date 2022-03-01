package io.github.cbinarycastle.betty.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.ui.navigation.MainDestinations
import io.github.cbinarycastle.betty.ui.navigation.MainNavGraph
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun BettyApp(viewModel: AppViewModel) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val topLevel = backStackEntry?.destination?.route == MainDestinations.Matches

    BackHandler(enabled = topLevel) { viewModel.exit() }

    BettyTheme {
        Scaffold {
            Column {
                MainNavGraph(
                    modifier = Modifier.weight(1f),
                    navController = navController
                )
                BannerAd()
            }
        }
    }
}

@Composable
private fun BannerAd() {
    AndroidView(
        factory = { context ->
            AdView(context).apply {
                adSize = AdSize.BANNER
                adUnitId = BuildConfig.ADMOB_BANNER_AD_UNIT_ID
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}