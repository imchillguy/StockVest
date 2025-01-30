package com.chillguy.stockvest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TwelveDataApiService {

    companion object {

        const val BASE_URL = "https://api.twelvedata.com/"

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