package com.example.submission1.ui.view

import com.example.submission1.data.model.MatchDetail

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<MatchDetail>?)
}