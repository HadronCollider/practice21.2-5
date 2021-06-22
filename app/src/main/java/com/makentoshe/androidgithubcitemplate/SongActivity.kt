package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.concurrent.thread

class SongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        var id = intent.getStringExtra("id")!!
        //var song: Song = Song("",0, Artist("",0))
        thread(start = true) {
           var song: Song = Parse.parsingSong(id)
            runOnUiThread {
                findViewById<TextView>(R.id.title).text = song.name
                findViewById<TextView>(R.id.group).text = song.artist.name
            }
        }
    }
}