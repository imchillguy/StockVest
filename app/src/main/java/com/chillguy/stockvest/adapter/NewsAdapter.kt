package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.NewsDiffUtilCallback
import com.chillguy.stockvest.model.NewsData
import com.chillguy.stockvest.viewholder.NewsViewHolder

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {

    var newsDataList: List<NewsData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<NewsData> = AsyncListDiffer(this, NewsDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item_view, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsDataList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(newsDataList[position])
    }
}