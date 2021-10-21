package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_main.view.*
import java.text.SimpleDateFormat

class NewsAdapter(val results : ArrayList<NewsModel.Result>)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        val publishStr = "Published At "
        val publishDate = result.publishedAt
        holder.view.date.text = publishStr + publishDate

        if(result.title.length > 35) {
            val tail = "..."
            val title = result.title.slice(0..35)
            holder.view.title.text = title + tail
        } else {
            holder.view.title.text = result.title
        }
        if(result.description.length > 70) {
            val tail = "..."
            val desc = result.description.slice(0..70)
            holder.view.textView.text = desc + tail
        } else {
            holder.view.textView.text = result.description
        }

        Glide.with(holder.view)
            .load(result.urlToImage)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .centerCrop()
            .into(holder.view.imageView)
    }

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    fun setData(data: List<NewsModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }
}