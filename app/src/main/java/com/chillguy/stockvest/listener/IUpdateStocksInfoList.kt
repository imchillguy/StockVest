package com.chillguy.stockvest.listener

import com.chillguy.stockvest.model.StocksInfoData

interface IUpdateStocksInfoList {

    fun update(stocksInfoData: List<StocksInfoData>)
}