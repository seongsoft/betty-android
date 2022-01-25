package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.league.FakeLeagueDataSource
import io.github.cbinarycastle.macao.data.league.LeagueDataSource
import io.github.cbinarycastle.macao.data.league.RemoteLeagueDataSource
import io.github.cbinarycastle.macao.data.match.details.FakeMatchDetailsDataSource
import io.github.cbinarycastle.macao.data.match.details.MatchDetailsDataSource
import io.github.cbinarycastle.macao.data.match.details.RemoteMatchDetailsSource

@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    @Binds
    fun bindMatchDetailsDataSource(dataSource: RemoteMatchDetailsSource): MatchDetailsDataSource

    @Binds
    fun bindLeagueDataSource(dataSource: RemoteLeagueDataSource): LeagueDataSource
}