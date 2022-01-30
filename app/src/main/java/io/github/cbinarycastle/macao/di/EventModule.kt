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
    fun provideAmplitudeApiKey() = "dbe7bbf79508b33bd56ef8f526179bc4"

    @HackleApiKey
    @Provides
    fun provideHackleApiKey() = "kLSiTrOcSypVj9LvBMA4m47RxugIe5iq"
}