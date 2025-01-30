package com.chillguy.stockvest.model.response

data class StocksDataResponse(
    val symbol: String,
    val price: Double,
    val changes: Double,
    val image: String,
    val companyName: String
)