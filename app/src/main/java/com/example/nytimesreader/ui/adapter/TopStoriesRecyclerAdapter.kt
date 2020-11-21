package com.example.nytimesreader.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimesreader.R
import com.example.nytimesreader.data.model.Story
import javax.sql.RowSetListener

class TopStoriesRecyclerAdapter(
    val values:List<Story>,
    val listener: onRowListener
):RecyclerView.Adapter<TopStoriesRecyclerAdapter.ViewHolder>(){
 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view = LayoutInflater.from(parent.context)
         .inflate(R.layout.single_row, parent, false)
     return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.published.text = item.published
        Glide.with(holder.imageView.context)
            .load(item.image)
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = values.size
    inner class ViewHolder(view: View, val rowListener: onRowListener):RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageView:ImageView = view.findViewById(R.id.listImage)
        val title:TextView = view.findViewById(R.id.listTitle)
        val published:TextView = view.findViewById(R.id.listPublished)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            rowListener.onRowClick(adapterPosition)
        }

    }
    interface onRowListener{
        fun onRowClick(position:Int)
    }
}