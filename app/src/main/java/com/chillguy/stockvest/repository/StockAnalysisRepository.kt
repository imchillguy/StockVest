package com.chillguy.stockvest.repository

import com.chillguy.stockvest.model.HorizontalBarData
import com.chillguy.stockvest.model.PriceTargetData
import com.chillguy.stockvest.model.StocksAnalysisData

class StockAnalysisRepository(
    val stocksSymbol: String
) {

    suspend fun getStocksAnalysisData(): StocksAnalysisData {
        var stocksAnalysisDetailList = listOf(
            HorizontalBarData(
                horizontalBarText = "Buy 15",
                horizontalBarWidth = 15f
            ),
            HorizontalBarData(
                horizontalBarText = "Hold 9",
                horizontalBarWidth = 9f
            ),
            HorizontalBarData(
                horizontalBarText = "Sell 6",
                horizontalBarWidth = 6f
            ),
        )

        // calculate total width
        var totalWidth = 0f
        stocksAnalysisDetailList.forEach { horizontalBarData ->
            totalWidth += horizontalBarData.horizontalBarWidth
        }
        if (totalWidth == 0f){
            totalWidth = 1f
        }
        stocksAnalysisDetailList = stocksAnalysisDetailList.map { horizontalBarData ->
            val mHorizontalBarData = horizontalBarData.copy(
                horizontalBarWidth = horizontalBarData.horizontalBarWidth / totalWidth
            )
            mHorizontalBarData
        }
        val stocksPriceTargetData = PriceTargetData(
            currentPrice = 600.0,
            targetPrice = 900.0,
            lowPrice = 550.0,
            highPrice = 1150.0
        )
        val stocksAnalysisData = StocksAnalysisData(
            stocksAnalysisText = "Rating Analysis From 30 Experts",
            stocksAnalysisDetailList = stocksAnalysisDetailList,
            stocksPriceAnalysisText = "Price Target Analysis",
            stocksPriceTargetData = stocksPriceTargetData
        )
        return stocksAnalysisData
    }

}