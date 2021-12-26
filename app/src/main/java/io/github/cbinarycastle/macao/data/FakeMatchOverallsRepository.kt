package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.entity.MatchOverall
import io.github.cbinarycastle.macao.domain.MatchOverallsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMatchOverallsRepository @Inject constructor() : MatchOverallsRepository {

    override suspend fun getMatchOveralls(): List<MatchOverall> {
        return matchOveralls
    }
}