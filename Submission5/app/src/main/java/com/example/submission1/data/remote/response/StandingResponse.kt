package com.example.submission1.data.remote.response

import com.example.submission1.data.entity.Standing
import com.google.gson.annotations.SerializedName

data class StandingResponse (
    @SerializedName("table")
    val standings: List<Standing>
)