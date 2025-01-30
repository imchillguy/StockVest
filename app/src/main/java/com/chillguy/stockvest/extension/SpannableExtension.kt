package com.chillguy.stockvest.extension

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import com.chillguy.stockvest.R
import com.chillguy.stockvest.enums.StocksMomentum

object SpannableExtension {

    fun SpannableString.setClickableSpan(color: Int,startIndex: Int, endIndex: Int, onClick : () -> Unit) {
        this.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = color
                    ds.isUnderlineText = false
                    ds.typeface = Typeface.DEFAULT
                }
                override fun onClick(p0: View) {
                    onClick.invoke()
                }
            },
            startIndex,
            endIndex,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

    fun Context.getStocksPriceColoredSpan(stocksPrice: String, stocksMomentum: StocksMomentum): SpannableString {
        val stocksPriceSpannable = SpannableString(stocksPrice)
        val colorSpan = when (stocksMomentum) {
            StocksMomentum.BULLISH -> {
                ForegroundColorSpan(this.getColor(R.color.green_80))
            }
            StocksMomentum.BEARISH -> {
                ForegroundColorSpan(this.getColor(R.color.claret_50))
            }
        }
        stocksPriceSpannable.setSpan(colorSpan, 0, stocksPrice.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        return stocksPriceSpannable
    }

    fun Context.getStocksInfoPriceColoredSpan(stocksPrice: String, stocksMomentum: StocksMomentum): SpannableString {
        val stocksPriceSpannable = SpannableString(stocksPrice)
        val colorSpan = when (stocksMomentum) {
            StocksMomentum.BULLISH -> {
                ForegroundColorSpan(this.getColor(R.color.green_50))
            }
            StocksMomentum.BEARISH -> {
                ForegroundColorSpan(this.getColor(R.color.claret_50))
            }
        }
        stocksPriceSpannable.setSpan(colorSpan, 0, stocksPrice.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        return stocksPriceSpannable
    }

}