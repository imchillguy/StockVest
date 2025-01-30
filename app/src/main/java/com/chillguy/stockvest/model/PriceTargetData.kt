package com.chillguy.stockvest.model

data class PriceTargetData(
    val currentPrice: Double,
    val targetPrice: Double,
    val lowPrice: Double,
    val highPrice: Double
)