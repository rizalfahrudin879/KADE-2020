package com.example.submission1.presenter

import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.remote.TheSportDBApi
import com.example.submission1.data.remote.response.TeamResponse
import com.example.submission1.ui.team.TeamView
import com.example.submission1.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getTeams(league)
                ).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }

    fun getTeamDetail(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getTeamDetail(id)
                ).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }

    fun getTeamDetailAway(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getTeamDetail(id)
                ).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamListAway(data.teams)
        }
    }

    fun getTeamSearchList(query: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getTeamSearch(query)
                ).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }
}