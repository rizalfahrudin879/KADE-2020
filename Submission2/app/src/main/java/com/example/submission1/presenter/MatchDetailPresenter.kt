package com.example.submission1.presenter


import com.example.submission1.data.response.MatchDetailResponse
import com.example.submission1.repository.ApiRepository
import com.example.submission1.repository.TheSportDBApi
import com.example.submission1.ui.view.MatchDetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(
    private val view: MatchDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getMatchDetail(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi().getMatchDetail(id)
                ),
                MatchDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.matchDetail)
            }
        }
    }
}