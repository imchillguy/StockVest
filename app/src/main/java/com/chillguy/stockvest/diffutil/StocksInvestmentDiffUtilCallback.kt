package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.StocksInvestmentData

class StocksInvestmentDiffUtilCallback: DiffUtil.ItemCallback<StocksInvestmentData>() {
    override fun areItemsTheSame(
        oldItem: StocksInvestmentData,
        newItem: StocksInvestmentData
    ): Boolean {
        return oldItem.stocksSymbol == newItem.stocksSymbol
    }

    override fun areContentsTheSame(
        oldItem: StocksInvestmentData,
        newItem: StocksInvestmentData
    ): Boolean {
        return oldItem == newItem
    }
}