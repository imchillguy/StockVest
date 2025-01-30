package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.TransactionsDayData

class TransactionDayDiffUtilCallback: DiffUtil.ItemCallback<TransactionsDayData>() {
    override fun areItemsTheSame(
        oldItem: TransactionsDayData,
        newItem: TransactionsDayData
    ): Boolean {
        return oldItem.day == newItem.day
    }

    override fun areContentsTheSame(
        oldItem: TransactionsDayData,
        newItem: TransactionsDayData
    ): Boolean {
        return oldItem == newItem
    }
}