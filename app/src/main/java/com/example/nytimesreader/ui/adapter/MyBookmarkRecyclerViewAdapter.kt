package com.example.nytimesreader.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.nytimesreader.R
import com.example.nytimesreader.data.model.Story
/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyBookmarkRecyclerViewAdapter(
    private val values: List<Story>,
    val listener: OnGridListener
) : RecyclerView.Adapter<MyBookmarkRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_bookmark, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.gridTitle.text = item.title
        holder.gridPublished.text = item.published
        Glide.with(holder.gridImage.context)
            .load(item.image)
            .centerCrop()
            .into(holder.gridImage)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View, val listener:OnGridListener) : RecyclerView.ViewHolder(view),View.OnClickListener  {
        val gridTitle: TextView = view.findViewById(R.id.gridTitle)
        val gridPublished: TextView = view.findViewById(R.id.gridPublished)
        val gridImage: ImageView = view.findViewById(R.id.gridImage)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onClick(adapterPosition)
        }


    }

    interface OnGridListener{
        fun onClick(position:Int)
    }
}