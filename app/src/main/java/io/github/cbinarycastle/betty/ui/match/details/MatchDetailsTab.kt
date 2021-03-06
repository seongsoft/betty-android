package io.github.cbinarycastle.betty.ui.match.details

import io.github.cbinarycastle.betty.R

enum class MatchDetailsTab(val titleResId: Int) {
    OVERVIEW(R.string.match_details_tab_overview),
    HOME_TEAM_MATCH_HISTORY(R.string.match_details_tab_home_team_match_history),
    AWAY_TEAM_MATCH_HISTORY(R.string.match_details_tab_away_team_match_history),
    RANKING(R.string.match_details_tab_ranking),
    UNDER_OVER(R.string.match_details_tab_under_over),
    GOALS_PER_MATCH(R.string.match_details_tab_goals_per_match),
}