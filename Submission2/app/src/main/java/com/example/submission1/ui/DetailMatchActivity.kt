package com.example.submission1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1.R
import com.example.submission1.data.model.MatchDetail
import com.example.submission1.data.model.Team
import com.example.submission1.presenter.MatchDetailPresenter
import com.example.submission1.presenter.TeamPresenter
import com.example.submission1.repository.ApiRepository
import com.example.submission1.ui.view.MainView
import com.example.submission1.ui.view.MatchDetailView
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(), MatchDetailView, MainView {

    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private lateinit var teamPresenter: TeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val id = intent.getStringExtra("id")

        val request = ApiRepository()
        val gson = Gson()
        matchDetailPresenter = MatchDetailPresenter(this, request, gson)
        matchDetailPresenter.getMatchDetail(id)

        teamPresenter = TeamPresenter(this, request, gson)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun showLoading() {
        progressBarDetailMatch?.visible()
    }

    override fun hideLoading() {
        progressBarDetailMatch?.invisible()
    }

    override fun showTeamList(data: List<Team>?) {
        Picasso.get().load(data?.get(0)?.teamBadge).fit().into(img_team_home)

    }

    override fun showTeamListAway(data: List<Team>?) {
        Picasso.get().load(data?.get(0)?.teamBadge).fit().into(img_team_away)
    }

    override fun showMatchDetail(data: List<MatchDetail>?) {
        if (data != null) {
            bind(data[0])
        }

    }

    private fun bind(matchDetail: MatchDetail) {
        name_team_home.text = matchDetail.strHomeTeam ?: "-"
        name_team_away.text = matchDetail.strAwayTeam ?: "-"
        score_team_home.text = matchDetail.intHomeScore ?: "-"
        score_team_away.text = matchDetail.intAwayScore ?: "-"
        name_team_home_.text = matchDetail.strHomeTeam ?: "-"
        name_team_away_.text = matchDetail.strAwayTeam ?: "-"
        name_match.text = matchDetail.strEvent ?: "-"
        date_match.text = matchDetail.dateEvent ?: "-"
        time_match.text = matchDetail.strTime ?: "-"
        team_home_formation.text = matchDetail.strHomeFormation ?: "-"
        team_away_formation.text = matchDetail.strAwayFormation ?: "-"
        team_home_shots.text = matchDetail.intHomeShots ?: "-"
        team_away_shots.text = matchDetail.intAwayShots ?: "-"
        team_home_keeper.text = matchDetail.strHomeLineupGoalkeeper.rep()
        team_away_keeper.text = matchDetail.strAwayLineupGoalkeeper.rep()
        team_home_defense.text = matchDetail.strHomeLineupDefense.rep()
        team_away_defense.text = matchDetail.strAwayLineupDefense.rep()
        team_away_midfield.text = matchDetail.strAwayLineupMidfield.rep()
        team_home_midfield.text = matchDetail.strHomeLineupMidfield.rep()
        team_away_forward.text = matchDetail.strAwayLineupForward.rep()
        team_home_forward.text = matchDetail.strHomeLineupForward.rep()
        team_away_substitutes.text = matchDetail.strAwayLineupSubstitutes.rep()
        team_home_substitutes.text = matchDetail.strHomeLineupSubstitutes.rep()
        team_home_goals.text = matchDetail.strHomeGoalDetails?.replace(";", "\n") ?: "-"
        team_away_goals.text = matchDetail.strAwayGoalDetails?.replace(";", "\n") ?: "-"

        teamPresenter.getTeamDetail(matchDetail.idHomeTeam)
        teamPresenter.getTeamDetailAway(matchDetail.idAwayTeam)

    }


    private fun String?.rep(): String?{
        return this?.replace("; ", "\n")?.replace(";", "\n") ?: "-"
    }

}