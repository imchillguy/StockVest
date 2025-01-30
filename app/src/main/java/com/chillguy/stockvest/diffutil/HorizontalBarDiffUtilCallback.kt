package com.chillguy.stockvest.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.chillguy.stockvest.model.HorizontalBarData

class HorizontalBarDiffUtilCallback: DiffUtil.ItemCallback<HorizontalBarData>() {
    override fun areItemsTheSame(oldItem: HorizontalBarData, newItem: HorizontalBarData): Boolean {
        return oldItem.horizontalBarText == newItem.horizontalBarText
    }

    override fun areContentsTheSame(
        oldItem: HorizontalBarData,
        newItem: HorizontalBarData
    ): Boolean {
        return oldItem == newItem
    }
}