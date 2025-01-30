package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.TransactionsDayData

class TransactionsDiffUtilCallBack: DiffUtil.ItemCallback<TransactionsDayData.TransactionsData>() {
    override fun areItemsTheSame(
        oldItem: TransactionsDayData.TransactionsData,
        newItem: TransactionsDayData.TransactionsData
    ): Boolean {
        return oldItem.stocksSymbol == newItem.stocksSymbol
    }

    override fun areContentsTheSame(
        oldItem: TransactionsDayData.TransactionsData,
        newItem: TransactionsDayData.TransactionsData
    ): Boolean {
        return oldItem == newItem
    }
}