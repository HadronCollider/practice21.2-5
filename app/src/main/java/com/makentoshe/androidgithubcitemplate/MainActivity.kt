package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout.Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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

    }
}
