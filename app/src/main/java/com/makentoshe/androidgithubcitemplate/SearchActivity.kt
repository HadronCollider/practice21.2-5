package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import java.io.File
import kotlin.concurrent.thread
import kotlin.math.min

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var intent = getIntent()
        var searchText = intent.getStringExtra("search")!!
        setContentView(R.layout.activity_search)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar: ProgressBar = findViewById<ProgressBar>(R.id.progress)
        findViewById<ImageButton>(R.id.button).setOnClickListener(View.OnClickListener { onBackPressed() })

        thread(start = true) {
            var songSearch = Parse.parsingSearch(searchText)
            val list: ArrayList<Item> = ArrayList()
            for (i in 0 until min(10, songSearch.size)) {
                list.add(
                    Item(
                        songSearch.get(i).name,
                        songSearch.get(i).artist.name,
                        songSearch.get(i).imgURL,
                        songSearch.get(i).id.toString()
                    )
                )
            }
            val context = this
            runOnUiThread {
                recyclerView.adapter = MyRecyclerViewAdapter(list, object: MyOnClickListener{
                    override fun onClicked(id: String) {
                        val intent = Intent(context, SongActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                        var result = true
                        var home = File(filesDir, "home.txt")
                        if (home.exists()) {
                            var idSongs = home.readText().trim().split(" ")
                            for (i in 0 until idSongs.size) {
                                if (id.equals(
                                        idSongs.get(i)
                                    )
                                ) result = false
                            }
                            if (result) {
                                home.appendText("$id ")
                            }
                        } else home.writeText("$id ")
                        findViewById<ImageButton>(R.id.heart).setImageResource(R.drawable.heartpressed)
                    }

                })
                progressBar.visibility = ProgressBar.INVISIBLE
                recycler_view.visibility = RecyclerView.VISIBLE
            }
        }
    }
}