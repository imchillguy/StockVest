package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.StocksInfoData

class StocksInfoDiffUtilCallback: DiffUtil.ItemCallback<StocksInfoData>() {
    override fun areItemsTheSame(oldItem: StocksInfoData, newItem: StocksInfoData): Boolean {
        return oldItem.stocksSymbol == newItem.stocksSymbol
    }

    override fun areContentsTheSame(oldItem: StocksInfoData, newItem: StocksInfoData): Boolean {
        return oldItem == newItem
    }
}