package com.chillguy.stockvest

import android.app.Application
import android.content.Context

class StockVestApplication: Application() {

    companion object {
        private lateinit var mApplicationContext: Context
        fun getApplicationContext() = mApplicationContext
    }

    override fun onCreate() {
        super.onCreate()
        StockVestApplication.mApplicationContext = applicationContext
    }

}