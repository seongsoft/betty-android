package io.github.cbinarycastle.betty.data.search

import io.github.cbinarycastle.betty.data.BackendService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSearchDataSource @Inject constructor(
    private val backendService: BackendService
) : SearchDataSource {

    override suspend fun search(keyword: String): List<String> {
        return backendService.searchTeams(keyword)
    }
}