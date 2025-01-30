package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.TransactionDayDiffUtilCallback
import com.chillguy.stockvest.model.TransactionsDayData
import com.chillguy.stockvest.viewholder.TransactionDayViewHolder

class TransactionDayAdapter: RecyclerView.Adapter<TransactionDayViewHolder>() {

    var transactionDayDataList: List<TransactionsDayData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<TransactionsDayData> = AsyncListDiffer(this, TransactionDayDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDayViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transactions_day_view, parent, false)
        return TransactionDayViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transactionDayDataList.size
    }

    override fun onBindViewHolder(holder: TransactionDayViewHolder, position: Int) {
        holder.bindData(transactionDayDataList[position])
    }
}