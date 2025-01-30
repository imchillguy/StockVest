package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.TableData

class TableDiffUtilCallback: DiffUtil.ItemCallback<TableData>() {
    override fun areItemsTheSame(oldItem: TableData, newItem: TableData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TableData, newItem: TableData): Boolean {
        return oldItem == newItem
    }

}