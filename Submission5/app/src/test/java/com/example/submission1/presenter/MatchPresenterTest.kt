package com.example.submission1.presenter

import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.remote.response.MatchResponse
import com.example.submission1.data.remote.response.MatchSearchResponse
import com.example.submission1.data.entity.Match
import com.example.submission1.ui.match.MatchView
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

class MatchPresenterTest {
    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>


    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getMatchListEventPass() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val idType = 0
        val id = "441613"
        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchList(idType, id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getMatchListEventNext() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val idType = 1
        val id = "441613"
        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchList(idType, id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getMatchSearchList() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchSearchResponse(match)
        val query = "arsenal"
        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchSearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchSearchList(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).hideLoading()

        }
    }
}