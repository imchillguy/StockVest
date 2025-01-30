package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chillguy.stockvest.R
import com.chillguy.stockvest.model.NewsData

class NewsViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {
    private lateinit var newsTitleTextView: TextView
    private lateinit var newsImageView: ImageView
    private lateinit var newsTimeTextView: TextView
    private lateinit var newsAuthorTextView: TextView

    init {
        with(mItemView) {
            newsTitleTextView = findViewById(R.id.news_title)
            newsImageView = findViewById(R.id.news_image)
            newsTimeTextView = findViewById(R.id.news_time)
            newsAuthorTextView = findViewById(R.id.news_author)
        }
    }

    fun bindData(newsData: NewsData) {
        with(newsData) {
            Glide.with(mItemView.context)
                .load(newsImageUrl)
                .centerCrop()
                .into(newsImageView)
            newsTitleTextView.text = newsTitle
            newsTimeTextView.text = newsTime
            newsAuthorTextView.text = newsAuthor
        }
    }

}