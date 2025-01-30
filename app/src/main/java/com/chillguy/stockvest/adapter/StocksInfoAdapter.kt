package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.StocksInfoDiffUtilCallback
import com.chillguy.stockvest.listener.IOnStocksClickListener
import com.chillguy.stockvest.model.StocksInfoData
import com.chillguy.stockvest.viewholder.StocksInfoViewHolder

class StocksInfoAdapter(private val listener: IOnStocksClickListener?): RecyclerView.Adapter<StocksInfoViewHolder>() {
    var stocksInfoDataList: List<StocksInfoData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<StocksInfoData> = AsyncListDiffer(this, StocksInfoDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stocks_info_item, parent, false)
        return StocksInfoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stocksInfoDataList.size
    }

    override fun onBindViewHolder(holder: StocksInfoViewHolder, position: Int) {
        holder.bindData(stocksInfoDataList[position])
        holder.itemView.setOnClickListener {
            listener?.onStocksClicked(stocksInfoDataList[position].stocksSymbol)
        }
    }

}