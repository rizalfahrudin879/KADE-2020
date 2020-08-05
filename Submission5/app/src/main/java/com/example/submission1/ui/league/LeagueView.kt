package com.example.submission1.ui.league

import com.example.submission1.data.entity.League

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)
}