package com.example.submission1.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.data.init.InitLeague
import com.example.submission1.data.model.Match
import com.example.submission1.presenter.MatchPresenter
import com.example.submission1.repository.ApiRepository
import com.example.submission1.ui.DetailMatchActivity
import com.example.submission1.ui.adapter.MatchAdapter
import com.example.submission1.ui.view.MatchView
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_tab_match.*
import org.jetbrains.anko.support.v4.startActivity

class TabMatchFragment : Fragment(), MatchView {
    companion object {
        const val ARG_STR = "str"
    }

    private var listLeague = InitLeague.league
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var matchPresenter: MatchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(ARG_STR)!!
        val positionTAB = arguments?.getInt("positionTAB")!!

        val id: String? = listLeague[position].idLeague

        Log.d("TAB-MATCH-P", position.toString())

        matchAdapter = MatchAdapter(match) {
            startActivity<DetailMatchActivity>("id" to it.idEvent)
        }

        val request = ApiRepository()
        val gson = Gson()
        matchPresenter = MatchPresenter(this, request, gson)

        matchPresenter.getMatchList(positionTAB, id)
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = matchAdapter
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