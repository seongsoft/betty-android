package io.github.cbinarycastle.macao.data

import io.github.cbinarycastle.macao.domain.MatchDetailsRepository
import io.github.cbinarycastle.macao.entity.MatchDetails

class FakeMatchDetailsRepository : MatchDetailsRepository {

    override suspend fun getMatchDetails(): MatchDetails {
        TODO("Not yet implemented")
    }
}