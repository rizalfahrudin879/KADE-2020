package com.example.submission1.ui.league

import com.example.submission1.data.model.League

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<League>)
}