package com.chillguy.stockvest.helper.api

import com.chillguy.stockvest.model.response.StocksDataResponse
import kotlinx.coroutines.flow.Flow

interface StocksInfoApiHelper {

    fun getStocksInfoData(stocksSymbol: String): Flow<List<StocksDataResponse>>

}