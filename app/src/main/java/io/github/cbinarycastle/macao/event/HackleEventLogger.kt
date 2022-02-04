package io.github.cbinarycastle.macao.event

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.cbinarycastle.macao.di.HackleSdkKey
import io.hackle.android.Hackle
import io.hackle.android.HackleApp
import io.hackle.android.event
import io.hackle.android.initialize
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HackleEventLogger @Inject constructor(
    @ApplicationContext private val context: Context,
    @HackleSdkKey private val sdkKey: String,
) : EventLogger {

    private var hackleApp: HackleApp? = null

    override fun initialize() {
        hackleApp = Hackle.initialize(context, sdkKey)

        if (hackleApp != null) {
            Timber.i("Hackle app is initialized.")
        }
    }

    override fun logEvent(event: Event) {
        if (hackleApp == null) {
            Timber.w("Hackle app is not initialized. Call initialize() before logging event.")
        } else {
            hackleApp?.track(Hackle.event(event.type) {
                event.properties.forEach {
                    if (it.value is Boolean) {
                        property(it.key, it.value as Boolean)
                    }
                    if (it.value is Double) {
                        property(it.key, it.value as Double)
                    }
                    if (it.value is Int) {
                        property(it.key, it.value as Int)
                    }
                    if (it.value is Long) {
                        property(it.key, it.value as Long)
                    }
                    if (it.value is String) {
                        property(it.key, it.value as String)
                    }
                }
            })
        }
    }
}