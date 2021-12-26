package io.github.cbinarycastle.macao.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.FakeMatchOverallsRepository
import io.github.cbinarycastle.macao.domain.MatchOverallsRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindMatchOverallsRepository(
        repository: FakeMatchOverallsRepository
    ): MatchOverallsRepository
}