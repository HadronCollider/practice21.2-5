package com.makentoshe.androidgithubcitemplate

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val icon: CircleImageView = view.findViewById(R.id.icon)
    val titleView: TextView = view.findViewById(R.id.title)
    val subscriprionView: TextView = view.findViewById(R.id.group)
}

data class Item(val title: String, val subscription: String, val imageId: Int)
