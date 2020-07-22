package com.example.submission1.data.response

import com.example.submission1.data.model.Match
import com.google.gson.annotations.SerializedName


data class MatchResponse(
    @SerializedName("events")
    val match: List<Match>
)
