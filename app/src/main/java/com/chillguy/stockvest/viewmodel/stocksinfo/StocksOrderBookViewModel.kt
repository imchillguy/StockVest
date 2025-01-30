package com.chillguy.stockvest.viewmodel.stocksinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.model.StocksPriceLotData
import com.chillguy.stockvest.model.TableData
import com.chillguy.stockvest.repository.StocksOrderBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StocksOrderBookViewModel(
    val stocksOrderBookRepository: StocksOrderBookRepository
): ViewModel() {

    val stocksOrderBookPriceLot = MutableLiveData<List<StocksPriceLotData>>()
    val stocksOrderBookBidAskLot = MutableLiveData<List<TableData>>()

    init {
        getStocksOrderBookPriceLot()
        getStocksOrderBookBidAskLot()
    }

    private fun getStocksOrderBookPriceLot(){
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksOrderBookPriceLot = stocksOrderBookRepository.getPriceLotData()
            stocksOrderBookPriceLot.postValue(mStocksOrderBookPriceLot)
        }
    }

    private fun getStocksOrderBookBidAskLot(){
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksOrderBookPriceLot = stocksOrderBookRepository.getBidAskLotData()
            stocksOrderBookBidAskLot.postValue(mStocksOrderBookPriceLot)
        }
    }

}