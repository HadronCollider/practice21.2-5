package com.makentoshe.androidgithubcitemplate

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread


class SongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        findViewById<ImageButton>(R.id.button).setOnClickListener(View.OnClickListener { onBackPressed() })
        val progressBar: ProgressBar = findViewById<ProgressBar>(R.id.progress)
        var id = intent.getStringExtra("id")!!
        //var song: Song = Song("",0, Artist("",0))
        thread(start = true) {
           var song: Song = Parse.parsingSong(id)
            runOnUiThread {
                findViewById<TextView>(R.id.title).text = song.name
                findViewById<TextView>(R.id.group).text = song.artist.name
                Picasso.with(this)
                    .load(song.imgURL)
                    .placeholder(R.drawable.black)
                    .error(R.drawable.black)
                    .into(findViewById<ImageView>(R.id.songImage))
                findViewById<TextView>(R.id.lyrics).text = song.lyrics
                progressBar.visibility = ProgressBar.INVISIBLE
            }
        }
    }
}