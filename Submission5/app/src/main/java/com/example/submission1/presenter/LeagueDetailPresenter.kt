package com.example.submission1.presenter

import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.remote.TheSportDBApi
import com.example.submission1.data.remote.response.LeagueResponse
import com.example.submission1.ui.league.LeagueView
import com.example.submission1.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueDetailPresenter(
    private val view: LeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueDetail(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getLeague(id)
                ).await(),
                LeagueResponse::class.java
            )

            view.hideLoading()
            view.showLeagueList(data.leagues)
        }
    }
}