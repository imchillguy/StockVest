package com.chillguy.stockvest.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chillguy.stockvest.R
import com.chillguy.stockvest.adapter.TransactionsAdapter
import com.chillguy.stockvest.model.TransactionsDayData

class TransactionDayViewHolder(private val mItemView: View): RecyclerView.ViewHolder(mItemView) {

    private lateinit var transactionDayTextView: TextView
    private lateinit var transactionRecyclerView: RecyclerView
    private lateinit var transactionsAdapter: TransactionsAdapter

    init {
        transactionsAdapter = TransactionsAdapter()
        with(mItemView) {
            transactionDayTextView = findViewById(R.id.transactions_tv)
            transactionRecyclerView = findViewById(R.id.transactions_rv)
            transactionRecyclerView.apply {
                adapter = transactionsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(mItemView.context, R.drawable.horizontal_divider_line)
                    ?.let { itemDecoration.setDrawable(it) }
                addItemDecoration(itemDecoration)
            }
        }
    }

    fun bindData(transactionsDayData: TransactionsDayData) {
        with(transactionsDayData) {
            transactionDayTextView.text = day
            transactionsAdapter.transactionsList = transactionsData
        }
    }

}