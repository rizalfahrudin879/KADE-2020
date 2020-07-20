package com.example.submission1.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission1.ui.fragment.TabMatchFragment


class TabMatchAdapter(fragment: Fragment, private val id: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = TabMatchFragment()
        fragment.arguments = Bundle().apply {
            putInt(TabMatchFragment.ARG_STR, id)
            putInt("positionTAB", position)
        }
        return fragment
    }
}