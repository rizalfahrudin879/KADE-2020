package com.example.submission1.data.entity

data class Favorite(
    val id: Long?,
    val matchId: String?,
    val matchName: String?,
    val matchDate: String?,
    val homeScore: String?,
    val awayScore: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "match_id"
        const val MATCH_NAME: String = "match_name"
        const val MATCH_DATE: String = "match_date"
        const val HOME_SCORE: String = "home_score"
        const val AWAY_SCORE: String = "away_score"
    }
}