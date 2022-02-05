package io.github.cbinarycastle.betty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.data.match.overall.DefaultMatchOverallPagingSourceFactory
import io.github.cbinarycastle.betty.data.match.overall.MatchOverallPagingSourceFactory

@InstallIn(SingletonComponent::class)
@Module
interface PagingModule {

    @Binds
    fun bindMatchOverallPagingSourceFactory(
        factory: DefaultMatchOverallPagingSourceFactory
    ): MatchOverallPagingSourceFactory
}