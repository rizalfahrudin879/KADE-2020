package com.example.submission1.ui.view

import com.example.submission1.data.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>?)
    fun showTeamListAway(data: List<Team>?)
}