package com.example.recyclerviewex2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptorClass(
val images: List<String>,
val names: List<String>) : RecyclerView.Adapter<AdaptorClass.AdaptorViewHolder>()  {
    
    class AdaptorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) 

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptorViewHolder {
    return AdaptorViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.layout_listitem,
            parent,
            false
        )
    )
    }


    override fun onBindViewHolder(holder: AdaptorViewHolder, position: Int) {
       Glide.with(holder.itemView.context).asBitmap()
           .load(images[position])
           .into(holder.itemView.findViewById<ImageView>(R.id.circleImage))
        holder.itemView.findViewById<TextView>(R.id.txtView).text = names[position]
    }

    override fun getItemCount(): Int {
        return names.size
    }
}