package com.example.submission1.presenter

import com.example.submission1.data.response.MatchResponse
import com.example.submission1.data.response.MatchSearchResponse
import com.example.submission1.repository.ApiRepository
import com.example.submission1.repository.TheSportDBApi
import com.example.submission1.ui.match.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getMatchList(idType: Int?, id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getMatch(idType, id)
                ),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.match)
            }
        }
    }

    fun getMatchSearchList(query: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getMatchSearch(query)
                ),
                MatchSearchResponse::class.java
            )

            uiThread {
                if (data.match.isNullOrEmpty()) {
                    view.hideLoading()
                } else {
                    val filterData = data.match.filter { it.strSport == "Soccer" }
                    if (!filterData.isNullOrEmpty()) {
                        view.showMatchList(filterData)
                        view.hideLoading()
                    } else {
                        view.hideLoading()
                    }
                }
            }
        }
    }
}