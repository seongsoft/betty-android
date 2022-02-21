package io.github.cbinarycastle.betty.ui

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import timber.log.Timber

@Composable
fun BettyApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val topLevel = backStackEntry?.destination?.route == MainDestinations.StartDestination
    var openFinishDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    var nativeAd: NativeAd? by remember { mutableStateOf(null) }

    val onBackPressedCallback = remember(nativeAd) {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backStackEntry?.destination?.route == MainDestinations.Matches) {
                    openFinishDialog = true
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner, onBackPressedDispatcherOwner) {
        onBackPressedDispatcherOwner
            ?.onBackPressedDispatcher
            ?.addCallback(lifecycleOwner, onBackPressedCallback)

        onDispose { onBackPressedCallback.remove() }
    }

    if (openFinishDialog) {
        AlertDialog(
            onDismissRequest = {
                openFinishDialog = false
            },
            title = {
                Text("종료하시겠습니까?")
            },
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
                    Button(onClick = { }) {
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
                            adUnitId = BuildConfig.ADMOB_BANNER_AD_UNIT_ID
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