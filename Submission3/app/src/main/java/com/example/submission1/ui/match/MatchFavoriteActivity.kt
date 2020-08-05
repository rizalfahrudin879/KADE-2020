package com.example.submission1.ui.match

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1.R

class MatchFavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_favorite)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.list_match_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val fragment = MatchTabFragment()
        val args = Bundle()
        args.putInt("favoriteCHECKs", 1)
        fragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_fm, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}