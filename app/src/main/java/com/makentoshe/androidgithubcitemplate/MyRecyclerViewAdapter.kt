package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyRecyclerViewAdapter(private val list: ArrayList<Item>,val onClickListener: MyOnClickListener) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, ViewTipe: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.my_view_holder, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleView.text = list[position].title
        holder.subscriprionView.text = list[position].subscription
        Picasso.with(holder.itemView.context)
            .load(list[position].imageURL)
            .placeholder(R.drawable.black)
            .error(R.drawable.black)
            .into(holder.icon)
        holder.id = list[position].id
        holder.holderLayout.setOnClickListener{
            onClickListener.onClicked(holder.id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}