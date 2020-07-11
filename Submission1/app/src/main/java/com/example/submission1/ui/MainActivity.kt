package com.example.submission1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.adapter.MainAdapter
import com.example.submission1.model.Team
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Home"

        initData()

        verticalLayout {
            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = MainAdapter(context, items) {
                    startActivity<DetailActivity>("id" to it.id)
                }
            }
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val img = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_description)
        items.clear()
        for (i in name.indices) {
            items.add(
                Team(
                    i,
                    name[i],
                    img.getResourceId(i, 0),
                    desc[i]
                )
            )
        }
        img.recycle()
    }
}