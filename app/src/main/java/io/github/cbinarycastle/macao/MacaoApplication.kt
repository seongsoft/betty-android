package io.github.cbinarycastle.macao

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import io.github.cbinarycastle.macao.event.CompositeEventLogger
import io.github.cbinarycastle.macao.event.EventLogger
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MacaoApplication : Application() {

    @Inject
    lateinit var eventLogger: EventLogger

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
        eventLogger.initialize()
    }
}