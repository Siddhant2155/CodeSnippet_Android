package com.example.recycleviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.model_item_layout.view.*

class ModelAdaptor(
    val model: ArrayList<Model>
) : RecyclerView.Adapter<ModelAdaptor.ModelViewHolder>() {

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder =
        ModelViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.model_item_layout,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.itemView.name.text = model[position].name
        holder.itemView.course.text = model[position].course
        holder.itemView.progress.text = "${model[position].completed}%"
    }

    override fun getItemCount(): Int = model.size
}