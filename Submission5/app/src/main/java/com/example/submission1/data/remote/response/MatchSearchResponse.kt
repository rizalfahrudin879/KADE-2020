package com.example.submission1.data.remote.response

import com.example.submission1.data.entity.Match
import com.google.gson.annotations.SerializedName


data class MatchSearchResponse(
    @SerializedName("event")
    val match: List<Match>
)
