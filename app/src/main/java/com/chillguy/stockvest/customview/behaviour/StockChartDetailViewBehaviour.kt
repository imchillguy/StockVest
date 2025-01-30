package com.chillguy.stockvest.customview.behaviour

import android.view.View

sealed class StockChartDetailViewBehaviour(
    val stocksInfoVisibility: Int,
    val stocksSymbolVisibility: Int,
    val stocksCategoryVisibility: Int,
    val stocksChartTimeFrameVisibility: Int,
    val stocksPriceLotVisibility: Int,
) {

    data object StockPriceChartView: StockChartDetailViewBehaviour(
        stocksInfoVisibility = View.GONE,
        stocksSymbolVisibility = View.VISIBLE,
        stocksCategoryVisibility = View.GONE,
        stocksChartTimeFrameVisibility = View.GONE,
        stocksPriceLotVisibility = View.VISIBLE
    )

    data object StockDetailPriceChartView: StockChartDetailViewBehaviour(
        stocksInfoVisibility = View.VISIBLE,
        stocksSymbolVisibility = View.GONE,
        stocksCategoryVisibility = View.VISIBLE,
        stocksChartTimeFrameVisibility = View.VISIBLE,
        stocksPriceLotVisibility = View.GONE
    )

}