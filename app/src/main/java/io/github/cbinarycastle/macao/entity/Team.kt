package io.github.cbinarycastle.macao.entity

data class Team(
    val name: String,
    val imageUrl: String,
    val lastOutcomes: List<Outcome>,
)