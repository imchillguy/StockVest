package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R

class StocksCategoryViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {

    private lateinit var stocksCategoryTextView: TextView

    init {
        stocksCategoryTextView = mItemView.findViewById(R.id.stocks_category_tv)
    }

    fun bindData(stocksCategory: String) {
        stocksCategoryTextView.text = stocksCategory
    }

}