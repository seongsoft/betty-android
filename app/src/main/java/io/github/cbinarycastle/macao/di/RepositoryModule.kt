package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.match.details.DefaultMatchDetailsRepository
import io.github.cbinarycastle.macao.data.match.list.DefaultMatchOverallsRepository
import io.github.cbinarycastle.macao.domain.MatchDetailsRepository
import io.github.cbinarycastle.macao.domain.MatchOverallsRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindMatchOverallsRepository(
        repository: DefaultMatchOverallsRepository
    ): MatchOverallsRepository

    @Binds
    fun bindMatchDetailsRepository(
        repository: DefaultMatchDetailsRepository
    ): MatchDetailsRepository
}