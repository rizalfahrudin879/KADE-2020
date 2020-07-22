package com.example.submission1.data.response

import com.example.submission1.data.model.MatchDetail
import com.google.gson.annotations.SerializedName


data class MatchDetailResponse(
    @SerializedName("events")
    val matchDetail: List<MatchDetail>
)
