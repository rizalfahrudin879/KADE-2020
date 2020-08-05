package com.example.submission1.data.api.response

import com.example.submission1.data.entity.Match
import com.google.gson.annotations.SerializedName


data class MatchResponse(
    @SerializedName("events")
    val match: List<Match>
)
