package com.example.submission1.presenter

import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.remote.TheSportDBApi
import com.example.submission1.data.remote.response.StandingResponse
import com.example.submission1.data.remote.response.TeamResponse
import com.example.submission1.ui.standing.StandingView
import com.example.submission1.ui.team.TeamView
import com.example.submission1.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter(
    private val view: StandingView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getStandingList(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getStanding(id)
                ).await(),
                StandingResponse::class.java
            )
            view.hideLoading()
            view.showStanding(data.standings)
        }
    }
}