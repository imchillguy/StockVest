package com.chillguy.stockvest.model.response

data class StocksCurrentPriceResponse(
    val symbol: String,
    val price: Double
)