package io.github.cbinarycastle.betty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.data.FakeLeagueDataSource
import io.github.cbinarycastle.betty.data.match.details.FakeMatchDetailsDataSource
import io.github.cbinarycastle.betty.data.league.LeagueDataSource
import io.github.cbinarycastle.betty.data.match.details.MatchDetailsDataSource

@InstallIn(SingletonComponent::class)
@Module
interface FakeDataSourceModule {

    @Binds
    fun bindMatchDetailsDataSource(dataSource: FakeMatchDetailsDataSource): MatchDetailsDataSource

    @Binds
    fun bindLeagueDataSource(dataSource: FakeLeagueDataSource): LeagueDataSource
}