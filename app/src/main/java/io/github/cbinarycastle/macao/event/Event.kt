package io.github.cbinarycastle.macao.event

enum class Event(val type: String) {
    MATCHES_LEAGUE_FILTER_CLICK("matches_leagueFilter_click"),
    MATCHES_MATCH_ITEM_CLICK("matches_matchItem_click"),
    MATCH_DETAILS_PLACE_TAB_CLICK("matchDetails_place_tab_click"),
    MATCH_DETAILS_HOME_TEAM_MATCH_HISTORY_TAB_CLICK("matchDetails_homeTeamMatchHistory_tab_click"),
    MATCH_DETAILS_AWAY_TEAM_MATCH_HISTORY_TAB_CLICK("matchDetails_awayTeamMatchHistory_tab_click"),
    MATCH_DETAILS_RANKING_TAB_CLICK("matchDetails_ranking_tab_click"),
    MATCH_DETAILS_UNDER_OVER_TAB_CLICK("matchDetails_underOver_tab_click"),
    MATCH_DETAILS_GOALS_PER_MATCH_TAB_CLICK("matchDetails_goalsPerMatch_tab_click"),
}