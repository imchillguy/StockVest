package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.model.HorizontalBarData

class HorizontalBarViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {
    private lateinit var horizontalBarFilledView: View
    private lateinit var horizontalBarEmptyView: View
    private lateinit var horizontalBarTextView: TextView

    init {
        with(mItemView) {
            horizontalBarFilledView = findViewById(R.id.horizontal_bar_filled_view)
            horizontalBarEmptyView = findViewById(R.id.horizontal_bar_empty_view)
            horizontalBarTextView = findViewById(R.id.horizontal_bar_tv)
        }
    }

    fun bindData(horizontalBarData: HorizontalBarData) {
        with(horizontalBarData) {
            horizontalBarTextView.text = horizontalBarText
            val filledLayoutParams = horizontalBarFilledView.layoutParams as LinearLayout.LayoutParams
            filledLayoutParams.weight = horizontalBarWidth
            horizontalBarFilledView.layoutParams = filledLayoutParams
            val emptyLayoutParams = horizontalBarEmptyView.layoutParams as LinearLayout.LayoutParams
            emptyLayoutParams.weight = 1f-horizontalBarWidth
            horizontalBarEmptyView.layoutParams = emptyLayoutParams
        }
    }

}