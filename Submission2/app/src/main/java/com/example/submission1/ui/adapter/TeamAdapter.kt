package com.example.submission1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1.R
import com.example.submission1.data.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*

class TeamAdapter(private val dataList: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: Team, listener: (Team) -> Unit) {
            tv_name_team.text = item.teamName
            Picasso.get().load(item.teamBadge).fit().into(img_badge_team)

            containerView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], listener)
    }
}