package io.github.cbinarycastle.betty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.event.AmplitudeEventLogger
import io.github.cbinarycastle.betty.event.CompositeEventLogger
import io.github.cbinarycastle.betty.event.EventLogger
import io.github.cbinarycastle.betty.event.HackleEventLogger

@InstallIn(SingletonComponent::class)
@Module
class EventModule {

    @Provides
    fun provideEventLogger(
        amplitudeEventLogger: AmplitudeEventLogger,
        hackleEventLogger: HackleEventLogger,
    ): EventLogger = CompositeEventLogger(
        listOf(
            amplitudeEventLogger,
            hackleEventLogger,
        )
    )
}