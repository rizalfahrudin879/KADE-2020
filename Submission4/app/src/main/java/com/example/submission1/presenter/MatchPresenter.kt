package com.example.submission1.presenter

import com.example.submission1.data.api.ApiRepository
import com.example.submission1.data.api.TheSportDBApi
import com.example.submission1.data.api.response.MatchResponse
import com.example.submission1.data.api.response.MatchSearchResponse
import com.example.submission1.ui.match.MatchView
import com.example.submission1.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()

) {
    fun getMatchList(idType: Int?, id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getMatch(idType, id)
                ).await(),
                MatchResponse::class.java
            )
            view.hideLoading()
            view.showMatchList(data.match)
        }
    }

    fun getMatchSearchList(query: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getMatchSearch(query)
                ).await(),
                MatchSearchResponse::class.java
            )
            view.hideLoading()
            view.showMatchList(data.match)
        }
    }
}