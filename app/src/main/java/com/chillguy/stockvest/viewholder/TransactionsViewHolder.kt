package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chillguy.stockvest.R
import com.chillguy.stockvest.enums.StocksTransactionStatus
import com.chillguy.stockvest.model.TransactionsDayData
import com.google.android.material.imageview.ShapeableImageView

class TransactionsViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {
    private lateinit var stocksImageView: ShapeableImageView
    private lateinit var stocksSymbolTextView: TextView
    private lateinit var stocksPriceTextView: TextView
    private lateinit var stocksLotTextView: TextView
    private lateinit var stocksTotalAmountTextView: TextView
    private lateinit var stocksTransactionStatusTextView: TextView

    init {
        with(mItemView) {
            stocksImageView = findViewById(R.id.stocks_icon_iv)
            stocksSymbolTextView = findViewById(R.id.stocks_symbol_tv)
            stocksPriceTextView = findViewById(R.id.stocks_price_tv)
            stocksLotTextView = findViewById(R.id.stocks_lot_tv)
            stocksTotalAmountTextView = findViewById(R.id.stocks_total_amount_tv)
            stocksTransactionStatusTextView = findViewById(R.id.stocks_transaction_status_tv)
        }
    }

    fun bindData(transactionsData: TransactionsDayData.TransactionsData) {
        with(transactionsData) {
            Glide.with(mItemView.context)
                .load(stocksIconUrl)
                .centerCrop()
                .into(stocksImageView)
            stocksSymbolTextView.text = stocksSymbol
            stocksPriceTextView.text = stocksPrice
            stocksLotTextView.text = stocksLot
            stocksTotalAmountTextView.text = stocksTotalAmount
            stocksTransactionStatusTextView.text = stocksTransactionStatus.status
            when (stocksTransactionStatus) {
                StocksTransactionStatus.OPEN -> {
                    stocksTransactionStatusTextView.setTextColor(ContextCompat.getColor(mItemView.context, R.color.red_50))
                }
                StocksTransactionStatus.MATCH -> {
                    stocksTransactionStatusTextView.setTextColor(ContextCompat.getColor(mItemView.context, R.color.green_50))
                }
                StocksTransactionStatus.WITHDRAWAl -> {
                    stocksTransactionStatusTextView.setTextColor(ContextCompat.getColor(mItemView.context, R.color.claret_50))
                }
            }
        }
    }

}