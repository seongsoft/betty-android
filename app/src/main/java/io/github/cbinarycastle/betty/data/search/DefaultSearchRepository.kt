package io.github.cbinarycastle.betty.data.search

import io.github.cbinarycastle.betty.domain.SearchRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSearchRepository @Inject constructor(
    private val dataSource: SearchDataSource
) : SearchRepository {

    override suspend fun searchTeamNames(keyword: String): List<String> {
        return dataSource.search(keyword)
    }
}