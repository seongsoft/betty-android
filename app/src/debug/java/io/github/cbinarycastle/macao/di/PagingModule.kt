package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.match.overall.DefaultMatchOverallPagingSourceFactory
import io.github.cbinarycastle.macao.data.match.overall.FakeMatchOverallPagingSourceFactory
import io.github.cbinarycastle.macao.data.match.overall.MatchOverallPagingSourceFactory

@InstallIn(SingletonComponent::class)
@Module
interface PagingModule {

    @Binds
    fun bindMatchOverallPagingSourceFactory(
        factory: DefaultMatchOverallPagingSourceFactory
    ): MatchOverallPagingSourceFactory
}