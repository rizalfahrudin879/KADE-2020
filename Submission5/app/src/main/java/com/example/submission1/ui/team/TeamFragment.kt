package com.example.submission1.ui.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.data.remote.ApiRepository
import com.example.submission1.data.entity.Team
import com.example.submission1.presenter.TeamPresenter
import com.example.submission1.ui.team.TeamDetailActivity.Companion.BADGE_TEAM
import com.example.submission1.ui.team.TeamDetailActivity.Companion.DESC_TEAM
import com.example.submission1.ui.team.TeamDetailActivity.Companion.NAME_TEAM
import com.example.submission1.util.init.InitLeague
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamView {

    companion object {
        fun newInstance(): TeamFragment {
            val fragment = TeamFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var teamPresenter: TeamPresenter
    private lateinit var teamAdapter: TeamAdapter
    private var listLeague = InitLeague.league
    private var teams: MutableList<Team> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position")!!
        val league: String? = listLeague[position].strLeague
        Log.d("TEAM-P", position.toString())

        val request = ApiRepository()
        val gson = Gson()
        teamPresenter = TeamPresenter(this, request, gson)
        teamPresenter.getTeamList(league)

        teamAdapter = TeamAdapter(teams) {
            startActivity<TeamDetailActivity>(
                NAME_TEAM to it.teamName,
                BADGE_TEAM to it.teamBadge,
                DESC_TEAM to it.teamDesc
            )
        }

        rv_team.layoutManager = LinearLayoutManager(context)
        rv_team.adapter = teamAdapter
    }

    override fun showLoading() {
        progressBarTeam?.visible()
    }

    override fun hideLoading() {
        progressBarTeam?.invisible()
    }

    override fun showTeamList(data: List<Team>?) {
        teams.clear()

        if (data != null) {
            teams.addAll(data)
            teamAdapter.notifyDataSetChanged()
        }

        if (teams.isNullOrEmpty()) {
            progressBarTeam?.invisible()
        }
    }

    override fun showTeamListAway(data: List<Team>?) {}
}