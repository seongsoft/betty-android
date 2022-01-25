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
import io.hackle.android.initialize
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

        amplitudeClient?.logEvent(event.type)
        hackleApp?.track(event.type)
    }

    private fun checkClientInitialized(vararg clients: Any?) {
        clients.forEach {
            checkNotNull(it) {
                "Client is not initialized. Call initialize() before logging event."
            }
        }
    }
}