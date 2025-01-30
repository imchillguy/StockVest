package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.NewsData

class NewsDiffUtilCallback: DiffUtil.ItemCallback<NewsData>() {
    override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
        return oldItem.newsTitle == newItem.newsTitle
    }

    override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
        return oldItem == newItem
    }
}