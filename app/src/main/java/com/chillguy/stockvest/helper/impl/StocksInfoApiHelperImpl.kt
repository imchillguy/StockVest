package com.chillguy.stockvest.helper.impl

import com.chillguy.stockvest.api.FetchStocksData
import com.chillguy.stockvest.constants.ApiConstants
import com.chillguy.stockvest.helper.api.StocksInfoApiHelper
import kotlinx.coroutines.flow.flow

class StocksInfoApiHelperImpl(private val stocksService: FetchStocksData) : StocksInfoApiHelper {
    override fun getStocksInfoData(stocksSymbol: String) = flow {
        repeat(1) {
            val stocksDataList = stocksService.fetchStocksData(
                stocksSymbols = stocksSymbol,
                apiKey = ApiConstants.FINANCIAL_MODEL_API_KEY
            )
            emit(stocksDataList)
            kotlinx.coroutines.delay(10000)
        }
    }
}