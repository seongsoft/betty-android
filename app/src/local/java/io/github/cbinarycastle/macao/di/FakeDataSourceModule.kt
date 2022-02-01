package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.league.FakeLeagueDataSource
import io.github.cbinarycastle.macao.data.league.LeagueDataSource
import io.github.cbinarycastle.macao.data.match.details.MatchDetailsDataSource

@InstallIn(SingletonComponent::class)
@Module
interface FakeDataSourceModule {

    @Binds
    fun bindMatchDetailsDataSource(dataSource: FakeMatchDetailsDataSource): MatchDetailsDataSource

    @Binds
    fun bindLeagueDataSource(dataSource: FakeLeagueDataSource): LeagueDataSource
}