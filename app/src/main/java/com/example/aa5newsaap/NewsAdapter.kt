package com.example.aa5newsaap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

// This is the ADAPTER required by the recycler view
class NewsAdapter(private val listener: onItemClicked) : RecyclerView.Adapter<NewsViewHolder>()
    {
        val items: ArrayList<News> = ArrayList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {       // returns viewHolder
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            val viewHolder = NewsViewHolder(view)
            view.setOnClickListener{
                listener.itemclicked(items[viewHolder.adapterPosition]) // fetching the position from array of items
            }
        return viewHolder
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val currentView = items[position]
            holder.titleView.text = currentView.title
            holder.autorview.text = currentView.author
            Glide.with(holder.itemView.context).load(currentView.imgURl).into(holder.image)
        }

        fun updateNews(updatedNews : ArrayList<News>){
            items.clear()
            items.addAll(updatedNews)

            notifyDataSetChanged()
        }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image : ImageView = itemView.findViewById(R.id.image)
    val titleView : TextView = itemView.findViewById(R.id.title)
    val autorview : TextView = itemView.findViewById(R.id.author)
//              " : "   is used to denote "type of"


}

interface onItemClicked{ // this is a call back integrated in class newsAdapter just like onclick listener
    fun itemclicked(item: News)
}