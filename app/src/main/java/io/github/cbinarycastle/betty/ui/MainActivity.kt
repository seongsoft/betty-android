package io.github.cbinarycastle.betty.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.SideEffect
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAdOptions
import dagger.hilt.android.AndroidEntryPoint
import io.github.cbinarycastle.betty.BuildConfig
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdLoader.Builder(this, BuildConfig.ADMOB_NATIVE_AD_UNIT_ID)
//            .forNativeAd { nativeAd = it }
            .forNativeAd { Timber.e("nativeAd: $it") }
            .withAdListener(object : AdListener() {
                override fun onAdLoaded() {
                    Timber.e("AdLoader onAdLoaded")
                }
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Timber.e("AdLoader error: $p0")
                }
            })
            .build()
            .loadAd(AdRequest.Builder().build())
        setContent {
            BettyApp()
        }
    }
}