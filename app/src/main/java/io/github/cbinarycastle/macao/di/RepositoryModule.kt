package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.league.DefaultLeagueRepository
import io.github.cbinarycastle.macao.data.match.details.DefaultMatchDetailsRepository
import io.github.cbinarycastle.macao.data.match.overall.DefaultMatchOverallRepository
import io.github.cbinarycastle.macao.domain.LeagueRepository
import io.github.cbinarycastle.macao.domain.MatchDetailsRepository
import io.github.cbinarycastle.macao.domain.MatchOverallRepository

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