package com.example.submission1.ui.match

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.submission1.R
import com.example.submission1.data.helper.database
import com.example.submission1.data.model.Favorite
import com.example.submission1.data.model.MatchDetail
import com.example.submission1.data.model.Team
import com.example.submission1.presenter.MatchDetailPresenter
import com.example.submission1.presenter.TeamPresenter
import com.example.submission1.repository.ApiRepository
import com.example.submission1.ui.team.TeamView
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(),
    MatchDetailView,
    TeamView {

    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var matchDetail: MatchDetail
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var idMatch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        idMatch = intent.getStringExtra("id")


        val request = ApiRepository()
        val gson = Gson()
        matchDetailPresenter = MatchDetailPresenter(this, request, gson)
        matchDetailPresenter.getMatchDetail(idMatch)

        favoriteState()

        teamPresenter = TeamPresenter(this, request, gson)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to matchDetail.idEvent,
                    Favorite.MATCH_NAME to matchDetail.strEvent,
                    Favorite.MATCH_DATE to matchDetail.dateEvent,
                    Favorite.HOME_SCORE to matchDetail.intHomeScore,
                    Favorite.AWAY_SCORE to matchDetail.intAwayScore
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("DETAIL-E", e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE,
                    "(MATCH_ID = {id})",
                    "id" to idMatch
                )
            }

        } catch (e: SQLiteConstraintException) {
            Log.d("DETAIL-E", e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to idMatch
                )
            val favorite = result.parseList(classParser<Favorite>())
            isFavorite = favorite.isNotEmpty()
        }
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
            matchDetail = data[0]
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


    private fun String?.rep(): String? {
        return this?.replace("; ", "\n")?.replace(";", "\n") ?: "-"
    }

}