package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.FakeMatchOverallPagingSourceFactory
import io.github.cbinarycastle.macao.data.MatchOverallPagingSourceFactory

@InstallIn(SingletonComponent::class)
@Module
interface FakePagingModule {

    @Binds
    fun bindMatchOverallPagingSourceFactory(
        factory: FakeMatchOverallPagingSourceFactory
    ): MatchOverallPagingSourceFactory
}