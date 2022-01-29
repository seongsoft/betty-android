package io.github.cbinarycastle.macao.event

import android.app.Application
import android.content.Context
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.cbinarycastle.macao.di.AmplitudeApiKey
import io.github.cbinarycastle.macao.di.HackleApiKey
import io.hackle.android.Hackle
import io.hackle.android.HackleApp
import io.hackle.android.event
import io.hackle.android.initialize
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventLogger @Inject constructor(
    private val application: Application,
    @ApplicationContext private val context: Context,
    @AmplitudeApiKey private val amplitudeApiKey: String,
    @HackleApiKey private val hackleApiKey: String,
) {
    private var amplitudeClient: AmplitudeClient? = null
    private var hackleApp: HackleApp? = null

    fun initialize() {
        amplitudeClient = Amplitude.getInstance()
            .initialize(context, amplitudeApiKey)
            .enableForegroundTracking(application)

        hackleApp = Hackle.initialize(context, hackleApiKey)
    }

    fun logEvent(event: Event) {
        checkClientInitialized(amplitudeClient, hackleApp)
        logAmplitudeEvent(event)
        logHackleEvent(event)
    }

    private fun checkClientInitialized(vararg clients: Any?) {
        clients.forEach {
            checkNotNull(it) {
                "Client is not initialized. Call initialize() before logging event."
            }
        }
    }

    private fun logAmplitudeEvent(event: Event) {
        val properties = JSONObject()
        event.properties.forEach {
            properties.put(it.key, it.value)
        }
        amplitudeClient?.logEvent(event.type, properties)
    }

    private fun logHackleEvent(event: Event) {
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