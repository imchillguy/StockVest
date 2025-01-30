package com.chillguy.stockvest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FinancialModelingApiService {

    companion object {

        private const val BASE_URL = "https://financialmodelingprep.com/"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return retrofit.create(serviceClass)
        }

    }

}