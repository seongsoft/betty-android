package io.github.cbinarycastle.betty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.data.match.overall.FakeMatchOverallPagingSourceFactory
import io.github.cbinarycastle.betty.data.match.overall.MatchOverallPagingSourceFactory

@InstallIn(SingletonComponent::class)
@Module
interface FakePagingModule {

    @Binds
    fun bindMatchOverallPagingSourceFactory(
        factory: FakeMatchOverallPagingSourceFactory
    ): MatchOverallPagingSourceFactory
}