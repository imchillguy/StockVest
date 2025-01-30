package com.chillguy.stockvest.viewholder

import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.customview.type.FilterOptionsViewType
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.utils.Utils.toPx

class FilterOptionsViewHolder(private val mItemView: View, private val type: String): RecyclerView.ViewHolder(mItemView) {

    private lateinit var filterOptionRootView: LinearLayout
    private lateinit var filterOptionTextView: TextView

    init {
        with(mItemView) {
            filterOptionRootView = findViewById(R.id.filter_options_root)
            filterOptionTextView = findViewById(R.id.filter_options_tv)
        }
    }

    fun bindData(filterOptionsData: FilterOptionsData){
        with(filterOptionsData) {
            filterOptionTextView.text = text
            when (type) {
                FilterOptionsViewType.SEPARATOR.type -> {
                    if (isSelected) {
                        filterOptionRootView.background = ContextCompat.getDrawable(mItemView.context, R.drawable.filled_rount_rect_black_80_radius_10)
                        val dp5 = mItemView.context.toPx(5).toInt()
                        val linearLayoutParams = (filterOptionRootView.layoutParams as RecyclerView.LayoutParams)
                        linearLayoutParams.setMargins(dp5, 0 , dp5, 0)
                        filterOptionTextView.setTextColor(mItemView.context.getColor(R.color.white))
                    } else {
                        filterOptionRootView.background = null
                        filterOptionTextView.setTextColor(mItemView.context.getColor(R.color.black_80))
                        val linearLayoutParams = (filterOptionRootView.layoutParams as RecyclerView.LayoutParams)
                        linearLayoutParams.setMargins(0, 0 , 0, 0)
                        val typeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_medium)
                        filterOptionTextView.typeface = typeface
                    }
                }
                FilterOptionsViewType.SPACE.type -> {
                    if (isSelected) {
                        filterOptionRootView.background = ContextCompat.getDrawable(mItemView.context, R.drawable.filled_rount_rect_black_80_radius_10)
                        filterOptionTextView.setTextColor(mItemView.context.getColor(R.color.white))
                    } else {
                        filterOptionRootView.background = ContextCompat.getDrawable(mItemView.context, R.drawable.filled_round_rect_black_20_radius_10)
                        filterOptionTextView.setTextColor(mItemView.context.getColor(R.color.black_80))
                    }
                }
            }
        }

    }

}