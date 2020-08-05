package com.example.submission1.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.submission1.R
import com.example.submission1.data.api.ApiRepository
import com.example.submission1.data.entity.Match
import com.example.submission1.presenter.MatchPresenter
import com.example.submission1.ui.league.LeagueDetailActivity
import com.example.submission1.ui.match.*
import com.example.submission1.ui.team.TeamFragment
import com.example.submission1.util.init.InitLeague
import com.example.submission1.util.invisible
import com.example.submission1.util.visible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(),
    MatchView {
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var searchViewItem: MenuItem
    private lateinit var matchPresenter: MatchPresenter
    private lateinit var matchAdapter: MatchAdapter
    private var match: MutableList<Match> = mutableListOf()
    private var livePosition: Int = 0
    private var listLeague = InitLeague.league
    private var testFMInit: Int = 1

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_league -> {
                    testFMInit = 1
                    searchViewItem.isVisible = false
                    addFragment(TeamFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_match -> {
                    testFMInit = 2
                    searchViewItem.isVisible = true
                    addFragment(MatchFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.app_football)
        supportActionBar?.elevation = 0f

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val initial = TeamFragment.newInstance()
        addFragment(initial)

        pagerAdapter = PagerAdapter(listLeague) {
            startActivity<LeagueDetailActivity>("id" to it.idLeague)
        }
        view_pager.adapter = pagerAdapter

        val request = ApiRepository()
        val gson = Gson()
        matchPresenter = MatchPresenter(this, request, gson)

        matchAdapter = MatchAdapter(match) {
            startActivity<MatchDetailActivity>("id" to it.idEvent)
        }
        rv_search.layoutManager = LinearLayoutManager(applicationContext)
        rv_search.adapter = matchAdapter

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                livePosition = position

                if (testFMInit == 1) addFragment(TeamFragment())
                else if (testFMInit == 2) addFragment(MatchFragment())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.app_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchViewItem = menu.findItem(R.id.search)
        searchViewItem.isVisible = false

        searchViewItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                parent_view.invisible()
                rv_search.visible()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                match.clear()
                matchAdapter.notifyDataSetChanged()
                rv_search.invisible()
                parent_view.visible()
                return true
            }
        })


        val searchView = searchViewItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = resources.getString(R.string.app_name)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                matchPresenter.getMatchSearchList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                startActivity<MatchFavoriteActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFragment(fragment: Fragment) {
        val args = Bundle()
        args.putInt("position", livePosition)
        fragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun showLoading() {
        rv_search.invisible()
        progressBar.visible()
    }

    override fun hideLoading() {
        rv_search.visible()
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>?) {
        match.clear()

        if (data != null) {
            val filterData = data.filter { it.strSport == "Soccer" }
            match.addAll(filterData)
            matchAdapter.notifyDataSetChanged()
        }

        if (match.isNullOrEmpty()) {
            rv_search.invisible()
        }
    }
}
