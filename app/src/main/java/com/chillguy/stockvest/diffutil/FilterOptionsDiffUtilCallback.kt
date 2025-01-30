package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.FilterOptionsData

class FilterOptionsDiffUtilCallback: DiffUtil.ItemCallback<FilterOptionsData>() {
    override fun areItemsTheSame(oldItem: FilterOptionsData, newItem: FilterOptionsData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FilterOptionsData,
        newItem: FilterOptionsData
    ): Boolean {
        return oldItem == newItem
    }
}