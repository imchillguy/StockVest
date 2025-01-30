package com.chillguy.stockvest.repository

import androidx.lifecycle.MutableLiveData
import com.chillguy.stockvest.api.FetchStocksData
import com.chillguy.stockvest.enums.StocksMomentum
import com.chillguy.stockvest.helper.impl.StocksInfoApiHelperImpl
import com.chillguy.stockvest.listener.IUpdateStocksInfoList
import com.chillguy.stockvest.model.StocksInfoData
import com.chillguy.stockvest.retrofit.FinancialModelingApiService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow

class HomeRepository {

    private val store: FirebaseFirestore by lazy { Firebase.firestore }

    fun fetchStocksSymbol(stocksSymbolsList: MutableLiveData<List<String>>){
        val stocksSymbolList = mutableListOf<String>()

        store.collection("stocks_symbol")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    stocksSymbolList.add(document.id)
                }
                stocksSymbolsList.postValue(stocksSymbolList)
            }
            .addOnFailureListener { }
    }

    suspend fun fetchStocksData(
        stocksSymbols: String,
        listener: IUpdateStocksInfoList
    ) {
        val stocksInfoApiHelperImpl = StocksInfoApiHelperImpl(FinancialModelingApiService.createService(FetchStocksData::class.java))
        stocksInfoApiHelperImpl
            .getStocksInfoData(stocksSymbols)
            .collect { stocksDataList ->
                val stocksInfoData = stocksDataList.map { stocks ->
                    val stocksTrend = if (stocks.changes < 0) StocksMomentum.BEARISH else StocksMomentum.BULLISH
                    val percentageChange = (stocks.changes * 100.0)/stocks.price
                    val stocksPriceChangeTrend =  if (stocks.changes < 0) "-" else "+"
                    StocksInfoData(
                        stocksName = stocks.companyName,
                        stocksSymbol = stocks.symbol,
                        stocksIconUrl = stocks.image,
                        stocksPrice = stocks.price.toString(),
                        stocksPriceChange = "${stocks.changes} (${stocksPriceChangeTrend}${String.format("%.4f", percentageChange)})",
                        stocksMomentum = stocksTrend
                    )
                }
                listener.update(stocksInfoData)
            }
    }

}