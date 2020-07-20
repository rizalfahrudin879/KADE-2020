package com.example.submission1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {
    companion object {
        const val NAME = "name"
        const val BADGE = "badge"
        const val DESC = "name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.detail_team)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(NAME)
        val badge = intent.getStringExtra(BADGE)
        val desc = intent.getStringExtra(DESC)

        name_league.text = name
        Picasso.get().load(badge).fit().into(img_league)
        desc_league.text = desc

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}