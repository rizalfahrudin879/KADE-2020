package com.example.submission1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1.R
import com.example.submission1.data.model.League
import com.example.submission1.presenter.LeagueDetailPresenter
import com.example.submission1.repository.ApiRepository
import com.example.submission1.ui.view.LeagueView
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity(), LeagueView {

    private lateinit var leagueDetailPresenter: LeagueDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.detail_league)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getStringExtra("id")

        val request = ApiRepository()
        val gson = Gson()
        leagueDetailPresenter = LeagueDetailPresenter(this, request, gson)
        leagueDetailPresenter.getLeagueList(id)
    }

    override fun showLoading() {
        progressBarLeague?.visible()
    }

    override fun hideLoading() {
        progressBarLeague?.invisible()
    }

    override fun showTeamList(data: List<League>) {
        bind(data[0])
    }

    private fun bind(data: League) {
        name_league.text = data.strLeague
        desc_league.text = data.strDescriptionEN
        Picasso.get().load(data.strBadge).fit().into(img_league)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}