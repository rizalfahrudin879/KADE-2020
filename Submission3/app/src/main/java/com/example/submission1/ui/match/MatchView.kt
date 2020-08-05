package com.example.submission1.ui.match

import com.example.submission1.data.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>?)
}