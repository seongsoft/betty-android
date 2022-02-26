package io.github.cbinarycastle.betty.domain

interface SearchRepository {

    suspend fun searchTeamNames(keyword: String): List<String>
}