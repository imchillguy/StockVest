package com.chillguy.stockvest.model

import com.chillguy.stockvest.enums.StocksTransactionStatus

data class TransactionsDayData(
    val day: String,
    val transactionsData: List<TransactionsData>
) {
    data class TransactionsData(
        val stocksSymbol: String,
        val stocksIconUrl: String,
        val stocksPrice: String,
        val stocksLot: String,
        val stocksTotalAmount: String,
        val stocksTransactionStatus: StocksTransactionStatus
    )
}