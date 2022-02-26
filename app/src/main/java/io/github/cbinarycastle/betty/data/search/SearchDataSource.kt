package io.github.cbinarycastle.betty.data.search

interface SearchDataSource {

    suspend fun search(keyword: String): List<String>
}