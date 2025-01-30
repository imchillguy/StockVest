package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.StocksInvestmentDiffUtilCallback
import com.chillguy.stockvest.model.StocksInvestmentData
import com.chillguy.stockvest.viewholder.StocksInvestmentViewHolder

class StocksInvestmentAdapter: RecyclerView.Adapter<StocksInvestmentViewHolder>() {

    var stocksInvestmentDataList: List<StocksInvestmentData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<StocksInvestmentData> = AsyncListDiffer(this, StocksInvestmentDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksInvestmentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stocks_investment_view, parent, false)
        return StocksInvestmentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stocksInvestmentDataList.size
    }

    override fun onBindViewHolder(holder: StocksInvestmentViewHolder, position: Int) {
        holder.bindData(stocksInvestmentDataList[position])
    }
}