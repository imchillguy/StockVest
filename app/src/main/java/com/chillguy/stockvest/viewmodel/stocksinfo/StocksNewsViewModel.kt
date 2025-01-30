package com.chillguy.stockvest.viewmodel.stocksinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.model.NewsData
import com.chillguy.stockvest.repository.StocksNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StocksNewsViewModel(
    val stocksNewsRepository: StocksNewsRepository
): ViewModel() {

    val stocksNewsDataList = MutableLiveData<List<NewsData>>()

    init {
        getStocksNewsData()
    }

    private fun getStocksNewsData(){
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksNewsDataList = stocksNewsRepository.getStocksNewsData()
            stocksNewsDataList.postValue(mStocksNewsDataList)
        }
    }

}