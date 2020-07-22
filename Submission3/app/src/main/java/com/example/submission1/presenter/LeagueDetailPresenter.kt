package com.example.submission1.presenter

import com.example.submission1.data.response.LeagueResponse
import com.example.submission1.repository.ApiRepository
import com.example.submission1.repository.TheSportDBApi
import com.example.submission1.ui.league.LeagueView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailPresenter(
    private val view: LeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getLeagueList(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getLeague(id)
                ),
                LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.leagues)
            }
        }
    }
}