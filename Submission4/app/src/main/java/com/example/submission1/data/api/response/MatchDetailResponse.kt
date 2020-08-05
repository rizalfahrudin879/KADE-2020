package com.example.submission1.data.api.response

import com.example.submission1.data.entity.MatchDetail
import com.google.gson.annotations.SerializedName


data class MatchDetailResponse(
    @SerializedName("events")
    val matchDetail: List<MatchDetail>
)
