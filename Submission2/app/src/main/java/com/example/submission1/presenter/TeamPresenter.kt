package com.example.submission1.presenter

import com.example.submission1.data.response.TeamResponse
import com.example.submission1.repository.ApiRepository
import com.example.submission1.repository.TheSportDBApi
import com.example.submission1.ui.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getTeams(league)
                ),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

    fun getTeamDetail(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getTeamDetail(id)
                ),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

    fun getTeamDetailAway(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getTeamDetail(id)
                ),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamListAway(data.teams)
            }
        }
    }
}