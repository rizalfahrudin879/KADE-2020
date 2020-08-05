package com.example.submission1.util.init

import com.example.submission1.data.entity.League

class InitLeague {
    companion object {
        val league: List<League>
            get() = mutableListOf(
                League(
                    idLeague = "4328",
                    strLeague = "English Premier League",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/yrywtr1422246014.png"
                ),
                League(
                    idLeague = "4329",
                    strLeague = "English League Championship",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/squsxv1422037114.png"
                ),
                League(
                    idLeague = "4331",
                    strLeague = "German Bundesliga",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/xuvyux1422060838.png"
                ),
                League(
                    idLeague = "4332",
                    strLeague = "Italian Serie A",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/wqsvyv1422279949.png"
                ),
                League(
                    idLeague = "4334",
                    strLeague = "French Ligue 1",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/sxxyyu1421447369.png"
                ),
                League(
                    idLeague = "4335",
                    strLeague = "Spanish La Liga",
                    strTrophy = "https://www.thesportsdb.com/images/media/league/trophy/vtsqsv1422280482.png"
                )
            )
    }
}