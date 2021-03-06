package com.example.submission1.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val idEvent: String? = null,
    val strEvent: String? = null,
    val strEventAlternate: String? = null,
    val strFilename: String? = null,
    val strSport: String? = null,
    val idLeague: String? = null,
    val strLeague: String? = null,
    val strSeason: String? = null,
    val strDescriptionEN: String? = null,
    val strHomeTeam: String? = null,
    val strAwayTeam: String? = null,
    val intHomeScore: String? = null,
    val intRound: String? = null,
    val intAwayScore: String? = null,
    val intSpectators: String? = null,
    val strHomeGoalDetails: String? = null,
    val strHomeRedCards: String? = null,
    val strHomeYellowCards: String? = null,
    val strHomeLineupGoalkeeper: String? = null,
    val strHomeLineupDefense: String? = null,
    val strHomeLineupMidfield: String? = null,
    val strHomeLineupForward: String? = null,
    val strHomeLineupSubstitutes: String? = null,
    val strHomeFormation: String? = null,
    val strAwayRedCards: String? = null,
    val strAwayYellowCards: String? = null,
    val strAwayGoalDetails: String? = null,
    val strAwayLineupGoalkeeper: String? = null,
    val strAwayLineupDefense: String? = null,
    val strAwayLineupMidfield: String? = null,
    val strAwayLineupForward: String? = null,
    val strAwayLineupSubstitutes: String? = null,
    val strAwayFormation: String? = null,
    val intHomeShots: String? = null,
    val intAwayShots: String? = null,
    val dateEvent: String? = null,
    val dateEventLocal: String? = null,
    val strDate: String? = null,
    val strTime: String? = null,
    val strTimeLocal: String? = null,
    val strTVStation: String? = null,
    val idHomeTeam: String? = null,
    val idAwayTeam: String? = null,
    val strResult: String? = null,
    val strCircuit: String? = null,
    val strCountry: String? = null,
    val strCity: String? = null,
    val strPoster: String? = null,
    val strFanart: String? = null,
    val strThumb: String? = null,
    val strBanner: String? = null,
    val strMap: String? = null,
    val strTweet1: String? = null,
    val strTweet2: String? = null,
    val strTweet3: String? = null,
    val strVideo: String? = null,
    val strPostponed: String? = null,
    val strLocked: String? = null
) : Parcelable
