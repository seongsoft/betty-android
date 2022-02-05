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

    @AmplitudeApiKey
    @Provides
    fun provideAmplitudeApiKey() = "dbe7bbf79508b33bd56ef8f526179bc4"

    @HackleSdkKey
    @Provides
    fun provideHackleSdkKey() = "kLSiTrOcSypVj9LvBMA4m47RxugIe5iq"

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