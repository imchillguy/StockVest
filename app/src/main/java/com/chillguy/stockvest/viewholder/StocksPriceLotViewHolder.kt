package com.chillguy.stockvest.viewholder

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.customview.type.StocksPriceLotViewType
import com.chillguy.stockvest.enums.StocksPriceLotType
import com.chillguy.stockvest.model.StocksPriceLotData

class StocksPriceLotViewHolder(private val mItemView: View, type: String): RecyclerView.ViewHolder(mItemView) {

    private lateinit var stocksPriceLotName: TextView
    private lateinit var stocksPriceLotValue: TextView
    private lateinit var mType: String

    init {
        with(mItemView) {
            stocksPriceLotName = findViewById(R.id.stocks_price_lot_name)
            stocksPriceLotValue = findViewById(R.id.stocks_price_lot_value)
        }
        mType = type
    }

    fun bindData(stocksPriceLotData: StocksPriceLotData) {
        with(stocksPriceLotData) {
            stocksPriceLotName.text = name
            val stockPriceLotValueSpannable = SpannableString(value)
            val stockPriceLotValueSpanColor = when (type) {
                StocksPriceLotType.DEFAULT -> {
                    ForegroundColorSpan(mItemView.context.getColor(R.color.black_80))
                }
                StocksPriceLotType.BULLISH -> {
                    ForegroundColorSpan(mItemView.context.getColor(R.color.green_50))
                }
                StocksPriceLotType.BEARISH -> {
                    ForegroundColorSpan(mItemView.context.getColor(R.color.claret_50))
                }
            }
            stockPriceLotValueSpannable.setSpan(stockPriceLotValueSpanColor, 0, value.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            stocksPriceLotValue.text = stockPriceLotValueSpannable
            when(mType) {
                StocksPriceLotViewType.NAME_BOLD.type -> {
                    val nameTypeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_semi_bold)
                    val valueTypeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_regular)
                    stocksPriceLotName.typeface = nameTypeface
                    stocksPriceLotValue.typeface = valueTypeface
                }
                StocksPriceLotViewType.VALUE_BOLD.type -> {
                    val nameTypeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_regular)
                    val valueTypeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_semi_bold)
                    stocksPriceLotName.typeface = nameTypeface
                    stocksPriceLotValue.typeface = valueTypeface
                }
                else -> {
                    val nameTypeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_semi_bold)
                    val valueTypeface = ResourcesCompat.getFont(mItemView.context, R.font.inter_regular)
                    stocksPriceLotName.typeface = nameTypeface
                    stocksPriceLotValue.typeface = valueTypeface
                }
            }
        }
    }


}