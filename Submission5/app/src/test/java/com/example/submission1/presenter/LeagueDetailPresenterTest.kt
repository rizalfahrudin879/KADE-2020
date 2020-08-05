package com.example.submission1.presenter

import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.remote.response.LeagueResponse
import com.example.submission1.data.entity.League
import com.example.submission1.ui.league.LeagueView
import com.example.submission1.util.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueDetailPresenterTest {

    @Mock
    private lateinit var view: LeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>


    private lateinit var presenter: LeagueDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = LeagueDetailPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getLeagueDetail() {
        val leagues: MutableList<League> = mutableListOf()
        val response = LeagueResponse(leagues)
        val id = "4329"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueDetail(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueList(response.leagues)
            Mockito.verify(view).hideLoading()
        }
    }
}