package com.example.submission1.presenter

import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.remote.response.MatchDetailResponse
import com.example.submission1.data.entity.MatchDetail
import com.example.submission1.ui.match.MatchDetailView
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

class MatchDetailPresenterTest {

    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>


    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchDetailPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getMatchDetail() {
        val match: MutableList<MatchDetail> = mutableListOf()
        val response = MatchDetailResponse(match)
        val id = "441613"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetail(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetail(response.matchDetail)
            Mockito.verify(view).hideLoading()

        }
    }
}