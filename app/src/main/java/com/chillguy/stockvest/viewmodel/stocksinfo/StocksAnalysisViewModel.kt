package com.chillguy.stockvest.viewmodel.stocksinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.model.StocksAnalysisData
import com.chillguy.stockvest.repository.StockAnalysisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StocksAnalysisViewModel(
    val stocksAnalysisRepository: StockAnalysisRepository
): ViewModel() {

    val stocksAnalysisData= MutableLiveData<StocksAnalysisData>()

    init {
        getStocksAnalysisData()
    }

    private fun getStocksAnalysisData() {
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksAnalysisData = stocksAnalysisRepository.getStocksAnalysisData()
            stocksAnalysisData.postValue(mStocksAnalysisData)
        }
    }
}