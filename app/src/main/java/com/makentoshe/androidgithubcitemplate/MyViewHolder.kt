package com.makentoshe.androidgithubcitemplate

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val holderLayout: ConstraintLayout = view.findViewById(R.id.holderLayout)
    val icon: CircleImageView = view.findViewById(R.id.icon)
    val titleView: TextView = view.findViewById(R.id.title)
    val subscriprionView: TextView = view.findViewById(R.id.group)
    var id: String = ""
}

data class Item(val title: String, val subscription: String, val imageURL: String, val id: String)
