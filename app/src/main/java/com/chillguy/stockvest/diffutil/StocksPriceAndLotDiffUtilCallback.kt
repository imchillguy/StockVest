package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.StocksPriceLotData

class StocksPriceAndLotDiffUtilCallback: DiffUtil.ItemCallback<StocksPriceLotData>() {
    override fun areItemsTheSame(
        oldItem: StocksPriceLotData,
        newItem: StocksPriceLotData
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: StocksPriceLotData,
        newItem: StocksPriceLotData
    ): Boolean {
        return oldItem == newItem
    }

}