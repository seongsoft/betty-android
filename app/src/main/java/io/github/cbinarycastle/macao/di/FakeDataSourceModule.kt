package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.FakeMatchDetailsDataSource
import io.github.cbinarycastle.macao.data.MatchDetailsDataSource

@InstallIn(SingletonComponent::class)
@Module
interface FakeDataSourceModule {

    @Binds
    fun bindMatchDetailsDataSource(dataSource: FakeMatchDetailsDataSource): MatchDetailsDataSource
}