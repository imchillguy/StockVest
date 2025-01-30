package com.chillguy.stockvest.model

data class StocksChartDetailData(
    val stocksInfoData: StocksInfoData,
    val stocksCategory: List<String>,
    val stocksChartData: List<Pair<String, Float>>,
    val stocksPriceLotData: List<StocksPriceLotData>,
    val stocksTimeFrameData: List<FilterOptionsData>
)