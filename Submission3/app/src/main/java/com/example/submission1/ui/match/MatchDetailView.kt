package com.example.submission1.ui.match

import com.example.submission1.data.model.MatchDetail

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<MatchDetail>?)
}