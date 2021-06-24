package com.makentoshe.androidgithubcitemplate

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*
import java.io.File
import kotlin.concurrent.thread


class SongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        findViewById<ImageButton>(R.id.button).setOnClickListener(View.OnClickListener { onBackPressed() })
        val progressBar: ProgressBar = findViewById<ProgressBar>(R.id.progress)
        scrollView.visibility = ScrollView.INVISIBLE
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
                scrollView.visibility = ScrollView.VISIBLE
                findViewById<ImageButton>(R.id.heart).setOnClickListener {
                    var result = true
                    var archive = File(filesDir, "archive.txt")
                    if (archive.exists()) {
                        var idSongs = archive.readText().trim().split(" ")
                        for (i in 0 until idSongs.size) {
                            if (id.equals(
                                    idSongs.get(i)
                                )
                            ) result = false
                        }
                        if (result) {
                            archive.appendText("$id ")
                        }
                    } else archive.writeText("$id ")
                    findViewById<ImageButton>(R.id.heart).setImageResource(R.drawable.heartpressed)
                }
            }
        }
    }
}