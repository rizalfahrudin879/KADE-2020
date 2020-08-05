package com.example.submission1.ui.match

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.data.api.ApiRepository
import com.example.submission1.data.entity.Favorite
import com.example.submission1.data.entity.Match
import com.example.submission1.data.local.database
import com.example.submission1.presenter.MatchPresenter
import com.example.submission1.util.init.InitLeague
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_tab_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

class MatchTabFragment : Fragment(), MatchView {
    companion object {
        const val ARG_STR = "str"
    }

    private var listLeague = InitLeague.league
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var matchPresenter: MatchPresenter
    private var favorites: MutableList<Favorite> = mutableListOf()
    private var favoriteCheck: Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val positionTAB = arguments?.getInt("positionTAB")!!
        favoriteCheck = arguments?.getInt("favoriteCHECKs")
        val request = ApiRepository()
        val gson = Gson()
        matchPresenter = MatchPresenter(this, request, gson)

        Log.d("position tab", positionTAB.toString())

        if (favoriteCheck == 0) {
            val position = arguments?.getInt(ARG_STR)!!
            val id: String? = listLeague[position].idLeague


            matchPresenter.getMatchList(positionTAB, id)
        } else if (favoriteCheck == 1) {
            loadFavorite()
        }
        matchAdapter = MatchAdapter(match) {
            startActivity<MatchDetailActivity>("id" to it.idEvent)
        }

        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = matchAdapter
    }

    override fun onResume() {
        super.onResume()
        if (favoriteCheck == 1) {
            loadFavorite()
            matchAdapter.notifyDataSetChanged()
        }
    }

    private fun loadFavorite() {
        progressBar_ma?.invisible()
        favorites.clear()
        match.clear()
        context?.database?.use {
            val result = select(
                Favorite.TABLE_FAVORITE
            )

            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
        }
        favorites.forEach {
            match.add(
                Match(
                    strSport = "Soccer",
                    idEvent = it.matchId,
                    strEvent = it.matchName,
                    dateEvent = it.matchDate,
                    intHomeScore = it.homeScore,
                    intAwayScore = it.awayScore
                )
            )
        }
    }

    override fun showLoading() {
        progressBar_ma?.visible()
    }

    override fun hideLoading() {
        progressBar_ma?.invisible()
    }

    override fun showMatchList(data: List<Match>?) {
        match.clear()

        if (data != null) {
            match.addAll(data)
            matchAdapter.notifyDataSetChanged()
        }

        if (match.isNullOrEmpty()) {
            progressBar_ma.invisible()
            rv_match.invisible()
        }
    }
}