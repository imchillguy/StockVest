package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chillguy.stockvest.R
import com.chillguy.stockvest.model.StocksInvestmentData
import com.google.android.material.imageview.ShapeableImageView

class StocksInvestmentViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {

    private lateinit var stocksImageVIew: ShapeableImageView
    private lateinit var stocksSymbolTextVIew: TextView
    private lateinit var stocksNameTextVIew: TextView
    private lateinit var stocksInvestmentTextVIew: TextView
    private lateinit var stockProfitLossTextVIew: TextView
    private lateinit var stocksAllocateTextVIew: TextView

    init {
        with(mItemView) {
            stocksImageVIew = findViewById(R.id.stocks_iv)
            stocksSymbolTextVIew = findViewById(R.id.stocks_symbol_tv)
            stocksNameTextVIew = findViewById(R.id.stocks_name_tv)
            stocksInvestmentTextVIew = findViewById(R.id.stocks_investment_tv)
            stockProfitLossTextVIew = findViewById(R.id.stocks_profit_loss_tv)
            stocksAllocateTextVIew = findViewById(R.id.stocks_allocate_tv)
        }
    }

    fun bindData(stocksInvestmentData: StocksInvestmentData) {
        with(stocksInvestmentData) {
            stocksSymbolTextVIew.text = stocksSymbol
            stocksNameTextVIew.text = stocksName
            Glide.with(mItemView.context)
                .load(stocksIconUrl)
                .centerCrop()
                .into(stocksImageVIew)
            stocksInvestmentTextVIew.text = stocksInvestment
            stockProfitLossTextVIew.text = stocksProfitLoss
            stocksAllocateTextVIew.text = stocksAllocate
        }
    }

}