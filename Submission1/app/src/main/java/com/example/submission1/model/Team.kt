package com.example.submission1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: Int,
    val name: String,
    val img: Int,
    val desc: String
) : Parcelable