package com.example.submission1.data.api.response

import com.example.submission1.data.entity.Match
import com.google.gson.annotations.SerializedName


data class MatchSearchResponse(
    @SerializedName("event")
    val match: List<Match>
)
