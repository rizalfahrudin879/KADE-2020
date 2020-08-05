package com.example.submission1.ui.standing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.data.entity.Standing
import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.presenter.StandingPresenter
import com.example.submission1.util.init.InitLeague
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standing.*

class StandingFragment : Fragment(), StandingView {
    companion object {
        fun newInstance(): StandingFragment {
            val fragment = StandingFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var standingPresenter: StandingPresenter
    private lateinit var standingAdapter: StandingAdapter
    private var listLeague = InitLeague.league
    private var standings: MutableList<Standing> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position")!!
        val idLeague: String? = listLeague[position].idLeague

        val request = ApiRepository()
        val gson = Gson()
        standingPresenter = StandingPresenter(this, request, gson)
        standingPresenter.getStandingList(idLeague)

        standingAdapter = StandingAdapter(standings){}

        rv_standing.layoutManager = LinearLayoutManager(context)
        rv_standing.adapter = standingAdapter
    }

    override fun showLoading() {
        progressBarStanding.visible()
    }

    override fun hideLoading() {
        progressBarStanding.invisible()
    }

    override fun showStanding(data: List<Standing>?) {
        standings.clear()

        if (data != null) {
            standings.addAll(data)
            standingAdapter.notifyDataSetChanged()
        }

        if (standings.isNullOrEmpty()) {
            progressBarStanding?.invisible()
        }
    }
}