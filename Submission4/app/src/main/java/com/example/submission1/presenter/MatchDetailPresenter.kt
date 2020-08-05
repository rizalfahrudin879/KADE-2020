package com.example.submission1.presenter


import com.example.submission1.data.api.ApiRepository
import com.example.submission1.data.api.TheSportDBApi
import com.example.submission1.data.api.response.MatchDetailResponse
import com.example.submission1.ui.match.MatchDetailView
import com.example.submission1.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(
    private val view: MatchDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchDetail(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi().getMatchDetail(id)
                ).await(),
                MatchDetailResponse::class.java
            )
            view.hideLoading()
            view.showMatchDetail(data.matchDetail)
        }
    }
}