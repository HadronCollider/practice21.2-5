package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout.Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var pager: ViewPager2 = findViewById<ViewPager2>(R.id.viewPager)
        var pageAdapter: FragmentStateAdapter = Adapter(this)
        pager.adapter = pageAdapter;

        TabLayoutMediator(
            findViewById<TabLayout>(R.id.tabLayout),
            findViewById<ViewPager2>(R.id.viewPager)
        ) { tab, position ->
            findViewById<ViewPager2>(R.id.viewPager).setCurrentItem(tab.position, true)
            if (position == 1)
                tab.setText("ARCHIVE")
            if (position == 0)
                tab.setText("HOME")
        }.attach()

        val intent = Intent(this, SearchActivity::class.java)
        var searchText: String = "";
        findViewById<SearchView>(R.id.search).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                searchText = text ?: "";
                if (!searchText.equals("")) {
                    var end = ""
                    for (a in searchText.split(" ")) {
                        end = end + a + "%20"
                    }
//                    searchText = searchText.substring(0, searchText.length - 3)
                    intent.putExtra("search", searchText)
                    startActivity(intent)
                }
                return false
            }



            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }
        })
    }
}
