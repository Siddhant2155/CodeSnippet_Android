package com.example.newsfeedapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class NewsAdaptor(private val list: List<ArticlesItem>, val delegateListener: NewsTransfer) : RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(data: ArticlesItem) {
            itemView.headingView.text = data.title
            itemView.authorLbl.text = data.author
            Glide.with(itemView.context)
                .load(data.urlToImage)
                .into(itemView.imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).
            inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(list[position])
        holder.itemView.imgView.setOnClickListener {
            list[position].url?.let { it1 -> this.delegateListener.showData(it1) }
        }
    }

    override fun getItemCount(): Int = list.size
}


interface NewsTransfer {
    fun showData(url: String)
}