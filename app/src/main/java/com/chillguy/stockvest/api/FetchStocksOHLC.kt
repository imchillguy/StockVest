package com.chillguy.stockvest.api

import com.chillguy.stockvest.constants.ApiConstants
import com.chillguy.stockvest.model.response.StocksPriceOHLC
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchStocksOHLC {

    @GET("time_series")
    suspend fun fetchStocksOHLC(
        @Query("apikey") apiKey: String = ApiConstants.TWELVE_API_KEY,
        @Query("interval") interval: String = "1min",
        @Query("symbol", encoded = true) symbol: String,
        @Query("outputSize") outputSize: Int = 30,
        @Query("format") format: String = "JSON",
    ): StocksPriceOHLC

}