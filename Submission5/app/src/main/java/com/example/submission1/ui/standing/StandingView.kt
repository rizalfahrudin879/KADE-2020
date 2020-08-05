package com.example.submission1.ui.standing

import com.example.submission1.data.entity.Standing

interface StandingView {
    fun showLoading()
    fun hideLoading()
    fun showStanding(data: List<Standing>?)
}