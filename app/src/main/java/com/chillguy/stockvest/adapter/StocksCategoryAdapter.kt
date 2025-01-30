package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.viewholder.StocksCategoryViewHolder

class StocksCategoryAdapter(
    private val stocksCategoryList: List<String>
): RecyclerView.Adapter<StocksCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stocks_category_item, parent, false)
        return StocksCategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stocksCategoryList.size
    }

    override fun onBindViewHolder(holder: StocksCategoryViewHolder, position: Int) {
        holder.bindData(stocksCategoryList[position])
    }
}