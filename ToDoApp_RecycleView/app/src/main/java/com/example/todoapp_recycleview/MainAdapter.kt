package com.example.todoapp_recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_layout.view.*

class MainAdapter(
    val list: ArrayList<String>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_layout,
                parent,
            false
            )
        )


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.itemView.txtValue.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}