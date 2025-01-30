package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.StocksPriceAndLotDiffUtilCallback
import com.chillguy.stockvest.model.StocksPriceLotData
import com.chillguy.stockvest.viewholder.StocksPriceLotViewHolder

class StocksPriceLotAdapter(val type: String): RecyclerView.Adapter<StocksPriceLotViewHolder>() {

    var stocksPriceLotDataList: List<StocksPriceLotData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<StocksPriceLotData> = AsyncListDiffer(this, StocksPriceAndLotDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksPriceLotViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stocks_price_lot_item, parent, false)
        return StocksPriceLotViewHolder(itemView, type)
    }

    override fun getItemCount(): Int {
        return stocksPriceLotDataList.size
    }

    override fun onBindViewHolder(holder: StocksPriceLotViewHolder, position: Int) {
        holder.bindData(stocksPriceLotDataList[position])
    }


}