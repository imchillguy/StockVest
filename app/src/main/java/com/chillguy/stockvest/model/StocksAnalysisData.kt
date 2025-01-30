package com.chillguy.stockvest.model

data class StocksAnalysisData(
    val stocksAnalysisText: String,
    val stocksAnalysisDetailList: List<HorizontalBarData>,
    val stocksPriceAnalysisText: String,
    val stocksPriceTargetData: PriceTargetData
)
