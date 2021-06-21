package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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

        TabLayoutMediator(findViewById<TabLayout>(R.id.tabLayout), findViewById<ViewPager2>(R.id.viewPager)) { tab, position ->  findViewById<ViewPager2>(R.id.viewPager).setCurrentItem(tab.position, true)
            if (position == 1)
                tab.setText("ARCHIVE")
            if (position == 0)
                tab.setText("HOME")
        }.attach()

        findViewById<SearchView>(R.id.search).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                println(text)
                return false
            }
            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }
        })
    }
    data class Item(val title: String, val subscription: String)

    class MyRecyclerViewAdapter(private val list: List<Item>) : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, ViewTipe: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.my_view_holder, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.titleView.text = list[position].title
            holder.subscriprionView.text = list[position].subscription
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: CircleImageView = view.findViewById(R.id.icon)
        val titleView: TextView = view.findViewById(R.id.title)
        val subscriprionView: TextView = view.findViewById(R.id.subscription)
    }


}
