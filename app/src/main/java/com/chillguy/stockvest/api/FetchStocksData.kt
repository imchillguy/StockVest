package com.chillguy.stockvest.api

import com.chillguy.stockvest.model.response.StocksDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FetchStocksData {

    @GET("api/v3/profile/{stocksSymbols}")
    suspend fun fetchStocksData(
        @Path("stocksSymbols") stocksSymbols: String,
        @Query("apikey") apiKey: String
    ): List<StocksDataResponse>

}