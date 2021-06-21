package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread
import kotlin.math.min

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var intent = getIntent()
        var searchText = intent.getStringExtra("search")!!
        setContentView(R.layout.activity_search)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        thread(start = true) {
            var songSearch = Parse.parsingSearch(searchText)
            val list: ArrayList<Item> = ArrayList()
            for (i in 0 until min(10, songSearch.size)) {
                list.add(
                    Item(
                        songSearch.get(i).artist.name,
                        songSearch.get(i).name,
                        R.drawable.black
                    )
                )
            }
            runOnUiThread {
                recyclerView.adapter = MyRecyclerViewAdapter(list)
            }
        }
    }
}