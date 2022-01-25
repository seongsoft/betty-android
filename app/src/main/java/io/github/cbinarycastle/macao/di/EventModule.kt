package io.github.cbinarycastle.macao.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class EventModule {

    @AmplitudeApiKey
    @Provides
    fun provideAmplitudeApiKey() = "077dc204072ce090b33e07bb3a3e3422"

    @HackleApiKey
    @Provides
    fun provideHackleApiKey() = "kLSiTrOcSypVj9LvBMA4m47RxugIe5iq"
}