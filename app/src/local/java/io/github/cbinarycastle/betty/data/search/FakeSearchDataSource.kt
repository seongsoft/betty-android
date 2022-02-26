package io.github.cbinarycastle.betty.data.search

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeSearchDataSource @Inject constructor() : SearchDataSource {

    override suspend fun search(keyword: String): List<String> {
        delay(1000)
        return listOf("a", "b", "c", "d")
    }
}