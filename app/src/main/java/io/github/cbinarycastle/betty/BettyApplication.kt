package io.github.cbinarycastle.betty

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import io.github.cbinarycastle.betty.event.EventLogger
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BettyApplication : Application() {

    @Inject
    lateinit var eventLogger: EventLogger

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
        eventLogger.initialize()
    }
}