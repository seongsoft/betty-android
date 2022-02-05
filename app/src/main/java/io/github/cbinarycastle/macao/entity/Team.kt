package io.github.cbinarycastle.macao.entity

data class Team(
    val originalName: String,
    val displayName: String,
    val imageUrl: String,
    val lastOutcomes: List<Outcome>,
)