package com.example.submission1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1.R
import com.example.submission1.data.model.Match
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.*

class MatchAdapter(private val dataList: List<Match>, private val listener: (Match) -> Unit) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: Match, listener: (Match) -> Unit) {
            if (item.strSport == "Soccer") {
                match_name.text = item.strEvent
                match_date.text = item.dateEvent
                score_home.text = item.intHomeScore ?: "-"
                score_away.text = item.intAwayScore ?: "-"
            }

            containerView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], listener)
    }
}