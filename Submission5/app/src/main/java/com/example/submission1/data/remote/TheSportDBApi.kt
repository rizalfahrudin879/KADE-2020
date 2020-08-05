package com.example.submission1.data.remote

import com.example.submission1.BuildConfig

class TheSportDBApi {
    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=${league?.replace(
            " ",
            "%20"
        )}"
    }

    fun getTeamDetail(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=$id"
    }

    fun getTeamSearch(query: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t=$query"
    }

    fun getLeague(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupleague.php?id=$id"
    }

    fun getMatch(idType: Int?, id: String?): String {
        val type = if (idType == 0) "eventspastleague.php" else "eventsnextleague.php"

        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/$type?id=$id"
    }

    fun getMatchSearch(query: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=${query?.replace(
            " ",
            "%20"
        )}"
    }

    fun getMatchDetail(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$id"
    }

    fun getStanding(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookuptable.php?l=$id"
    }
}