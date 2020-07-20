package com.example.submission1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.submission1.R
import com.example.submission1.ui.adapter.TabMatchAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_match.view.*

class MatchFragment : Fragment() {

    companion object {
        fun newInstance(): MatchFragment {
            val fragment = MatchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var tabColl: TabMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val argPosition = arguments?.getInt("position")!!

        tabColl = TabMatchAdapter(this, argPosition)

        view.view_pager2?.adapter = tabColl
        val title = resources.getStringArray(R.array.title_tab)

        TabLayoutMediator(view.tab_detail, view.view_pager2) { tab, position ->
            tab.text = title[position]
        }.attach()
    }
}
