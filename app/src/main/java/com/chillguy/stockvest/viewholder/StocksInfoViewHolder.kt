package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chillguy.stockvest.R
import com.chillguy.stockvest.extension.SpannableExtension.getStocksInfoPriceColoredSpan
import com.chillguy.stockvest.listener.IOnStocksClickListener
import com.chillguy.stockvest.model.StocksInfoData
import com.google.android.material.imageview.ShapeableImageView

class StocksInfoViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {

    private lateinit var stocksImageVIew: ShapeableImageView
    private lateinit var stocksSymbolTextVIew: TextView
    private lateinit var stocksNameTextVIew: TextView
    private lateinit var stocksPriceTextVIew: TextView
    private lateinit var stocksPriceChangeTextVIew: TextView

    init {
        with(mItemView) {
            stocksImageVIew = findViewById(R.id.stocks_iv)
            stocksSymbolTextVIew = findViewById(R.id.stocks_symbol_tv)
            stocksNameTextVIew = findViewById(R.id.stocks_name_tv)
            stocksPriceTextVIew = findViewById(R.id.stocks_price_tv)
            stocksPriceChangeTextVIew = findViewById(R.id.stocks_price_change_tv)
        }
    }

    fun bindData(stocksInfoData: StocksInfoData){
        with(stocksInfoData) {
            stocksSymbolTextVIew.text = stocksSymbol
            stocksNameTextVIew.text = stocksName
            stocksPriceTextVIew.text = stocksPrice
            stocksPriceChangeTextVIew.text = mItemView.context.getStocksInfoPriceColoredSpan(stocksPriceChange, stocksMomentum)
            Glide.with(mItemView.context)
                .load(stocksIconUrl)
                .centerCrop()
                .into(stocksImageVIew)
        }
    }

}