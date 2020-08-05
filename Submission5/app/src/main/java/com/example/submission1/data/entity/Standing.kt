package com.example.submission1.data.entity

data class Standing (
    val name: String,
    val teamid: String,
    val played: Long,
    val goalsfor: Long,
    val goalsagainst: Long,
    val goalsdifference: Long,
    val win: Long,
    val draw: Long,
    val loss: Long,
    val total: Long
)
