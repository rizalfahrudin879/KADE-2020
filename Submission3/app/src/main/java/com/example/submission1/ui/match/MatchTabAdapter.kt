package com.example.submission1.ui.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class MatchTabAdapter(fragment: Fragment, private val id: Int?) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = MatchTabFragment()
        fragment.arguments = Bundle().apply {

            if (id != null) {
                putInt(MatchTabFragment.ARG_STR, id)
            }
            putInt("positionTAB", position)


        }
        return fragment
    }
}