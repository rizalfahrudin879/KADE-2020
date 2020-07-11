package com.example.submission1.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra("id", 0)

        val name = resources.getStringArray(R.array.club_name)[id]
        val img = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_description)[id]

        val imgX = img.getResourceId(id, 0)
        img.recycle()

        verticalLayout {
            padding = dip(60)

            textView {
                text = name
                textSize = 24f
                textColor = Color.BLACK
                width = wrapContent
                gravity = Gravity.CENTER_HORIZONTAL
            }

            Picasso.get().load(imgX).resize(300,300).into(
                imageView().lparams(width = matchParent, height = wrapContent) {
                    margin = dip(24)
                }
            )

            textView {
                text = desc
                textSize = 14f
                width = wrapContent
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}