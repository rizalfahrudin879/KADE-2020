package com.example.submission1.ui.team

import com.example.submission1.data.entity.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>?)
    fun showTeamListAway(data: List<Team>?)
}