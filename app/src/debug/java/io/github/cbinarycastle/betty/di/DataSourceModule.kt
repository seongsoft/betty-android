package io.github.cbinarycastle.betty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.data.league.LeagueDataSource
import io.github.cbinarycastle.betty.data.league.RemoteLeagueDataSource
import io.github.cbinarycastle.betty.data.match.details.MatchDetailsDataSource
import io.github.cbinarycastle.betty.data.match.details.RemoteMatchDetailsSource

@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    @Binds
    fun bindMatchDetailsDataSource(dataSource: RemoteMatchDetailsSource): MatchDetailsDataSource

    @Binds
    fun bindLeagueDataSource(dataSource: RemoteLeagueDataSource): LeagueDataSource
}