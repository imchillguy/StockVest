package com.chillguy.stockvest.model

import com.chillguy.stockvest.enums.StocksMomentum

data class StocksInfoData(
    val stocksName: String,
    val stocksSymbol: String,
    val stocksIconUrl: String,
    val stocksPrice: String,
    val stocksPriceChange: String,
    val stocksMomentum: StocksMomentum
)