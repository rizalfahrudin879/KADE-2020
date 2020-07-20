package com.example.submission1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1.R
import com.example.submission1.data.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_league.*

class PagerAdapter(private val dataList: List<League>, private val listener: (League) -> Unit) :
    RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: League, listener: (League) -> Unit) {
            tv_name.text = item.strLeague
            Picasso.get().load(item.strTrophy).fit().into(img_trophy)

            containerView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], listener)
    }
}