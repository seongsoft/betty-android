package io.github.cbinarycastle.betty.di

import javax.inject.Qualifier

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class MainDispatcher

@Qualifier
annotation class IoDispatcher