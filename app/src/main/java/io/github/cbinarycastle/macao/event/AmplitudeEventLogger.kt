package io.github.cbinarycastle.macao.event

import android.app.Application
import android.content.Context
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.cbinarycastle.macao.di.AmplitudeApiKey
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AmplitudeEventLogger @Inject constructor(
    private val application: Application,
    @ApplicationContext private val context: Context,
    @AmplitudeApiKey private val apiKey: String,
) : EventLogger {

    private var client: AmplitudeClient? = null

    override fun initialize() {
        client = Amplitude.getInstance()
            .initialize(context, apiKey)
            .enableForegroundTracking(application)

        if (client != null) {
            Timber.i("Amplitude client is initialized.")
        }
    }

    override fun logEvent(event: Event) {
        val properties = JSONObject()
        event.properties.forEach {
            properties.put(it.key, it.value)
        }

        if (client == null) {
            Timber.w("Amplitude client is not initialized. Call initialize() before logging event.")
        } else {
            client?.logEvent(event.type, properties)
        }
    }
}