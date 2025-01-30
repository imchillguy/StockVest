package com.chillguy.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.diffutil.TransactionsDiffUtilCallBack
import com.chillguy.stockvest.model.TransactionsDayData
import com.chillguy.stockvest.viewholder.TransactionsViewHolder

class TransactionsAdapter: RecyclerView.Adapter<TransactionsViewHolder>() {

    var transactionsList: List<TransactionsDayData.TransactionsData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ: AsyncListDiffer<TransactionsDayData.TransactionsData> = AsyncListDiffer(this, TransactionsDiffUtilCallBack())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transactions_item, parent, false)
        return TransactionsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.bindData(transactionsList[position])
    }
}