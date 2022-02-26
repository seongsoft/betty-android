package io.github.cbinarycastle.betty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.data.FakeLeagueDataSource
import io.github.cbinarycastle.betty.data.league.LeagueDataSource
import io.github.cbinarycastle.betty.data.match.details.FakeMatchDetailsDataSource
import io.github.cbinarycastle.betty.data.match.details.MatchDetailsDataSource
import io.github.cbinarycastle.betty.data.search.FakeSearchDataSource
import io.github.cbinarycastle.betty.data.search.SearchDataSource

@InstallIn(SingletonComponent::class)
@Module
interface FakeDataSourceModule {

    @Binds
    fun bindLeagueDataSource(dataSource: FakeLeagueDataSource): LeagueDataSource

    @Binds
    fun bindSearchDataSource(dataSource: FakeSearchDataSource): SearchDataSource

    @Binds
    fun bindMatchDetailsDataSource(dataSource: FakeMatchDetailsDataSource): MatchDetailsDataSource
}