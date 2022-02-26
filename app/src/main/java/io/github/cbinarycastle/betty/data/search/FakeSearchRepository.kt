package io.github.cbinarycastle.betty.data.search

import io.github.cbinarycastle.betty.domain.SearchRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeSearchRepository @Inject constructor() : SearchRepository {

    override suspend fun searchTeamNames(keyword: String): List<String> {
        delay(1000)
        return listOf("a", "b", "c", "d")
    }
}