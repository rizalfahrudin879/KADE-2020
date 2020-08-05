package com.example.submission1.data.remote.response

import com.example.submission1.data.entity.Match
import com.google.gson.annotations.SerializedName


data class MatchResponse(
    @SerializedName("events")
    val match: List<Match>
)
