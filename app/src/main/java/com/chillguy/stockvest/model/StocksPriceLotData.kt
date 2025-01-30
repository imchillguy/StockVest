package com.chillguy.stockvest.model

import com.chillguy.stockvest.enums.StocksPriceLotType

data class StocksPriceLotData(
    val name: String,
    val value: String,
    val type: StocksPriceLotType = StocksPriceLotType.DEFAULT,
)
