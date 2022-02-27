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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.ui.navigation.MainNavGraph
import io.github.cbinarycastle.betty.ui.navigation.MainDestinations
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun BettyApp(viewModel: AppViewModel) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    var openFinishDialog by remember { mutableStateOf(false) }
    var nativeAd: NativeAd? by remember { mutableStateOf(null) }

    val topLevel = backStackEntry?.destination?.route == MainDestinations.Matches
    BackHandler(enabled = topLevel) {
        openFinishDialog = true
    }

    if (openFinishDialog) {
        AlertDialog(
            onDismissRequest = { openFinishDialog = false },
            title = { Text("종료하시겠습니까?") },
            text = {
                AndroidView(
                    factory = { context ->
                        NativeAdView(context).apply {
                            nativeAd?.let { setNativeAd(it) }
                        }
                    }
                )
            },
            buttons = {
                Row {
                    Button(onClick = { viewModel.finish() }) {
                        Text("예")
                    }
                    Button(onClick = { openFinishDialog = false }) {
                        Text("아니요")
                    }
                }
            },
        )
    }

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