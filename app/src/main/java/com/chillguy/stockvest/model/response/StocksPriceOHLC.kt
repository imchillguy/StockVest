package com.chillguy.stockvest.model.response

data class StocksPriceOHLC(
    val values: List<OHLCData>
) {
    data class OHLCData(
        val datetime: String,
        val open: String,
        val high: String,
        val low: String,
        val close: String
    )
}
