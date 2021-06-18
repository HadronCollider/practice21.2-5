package com.example.tablayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.makentoshe.androidgithubcitemplate.Archive
import com.makentoshe.androidgithubcitemplate.Home

class Adapter(fragmentActivity: FragmentActivity?) : FragmentStateAdapter(fragmentActivity!!) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {return Home()
            }
            1 -> {return Archive()
            }
            else -> {return Home()
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}