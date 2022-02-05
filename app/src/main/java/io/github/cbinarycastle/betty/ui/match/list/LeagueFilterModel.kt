package io.github.cbinarycastle.betty.ui.match.list

sealed class LeagueFilterModel(
    val id: Long?,
    val name: String
) {
    object All : LeagueFilterModel(
        id = null,
        name = "All"
    )

    class League(
        id: Long,
        name: String,
        val imageUrl: String,
    ) : LeagueFilterModel(id, name)
}