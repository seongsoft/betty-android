package io.github.cbinarycastle.betty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.data.league.DefaultLeagueRepository
import io.github.cbinarycastle.betty.data.match.details.DefaultMatchDetailsRepository
import io.github.cbinarycastle.betty.data.match.overall.DefaultMatchOverallRepository
import io.github.cbinarycastle.betty.domain.LeagueRepository
import io.github.cbinarycastle.betty.domain.MatchDetailsRepository
import io.github.cbinarycastle.betty.domain.MatchOverallRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindMatchOverallsRepository(
        repository: DefaultMatchOverallRepository
    ): MatchOverallRepository

    @Binds
    fun bindMatchDetailsRepository(
        repository: DefaultMatchDetailsRepository
    ): MatchDetailsRepository

    @Binds
    fun bindLeagueRepository(repository: DefaultLeagueRepository): LeagueRepository
}