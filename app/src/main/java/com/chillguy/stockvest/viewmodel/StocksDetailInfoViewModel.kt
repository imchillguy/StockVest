package com.chillguy.stockvest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.model.FilterOptionsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StocksDetailInfoViewModel: ViewModel() {

    val stocksDetailInfoFilterList = MutableLiveData<List<FilterOptionsData>>()
    private var selectedPosition : Int = -1

    fun setFilterOptionsDataList(stocksFilteredOptionsList: List<FilterOptionsData>) {
        viewModelScope.launch(Dispatchers.IO) {
            stocksFilteredOptionsList.forEachIndexed { index, filterOptionsData ->
                if (filterOptionsData.isSelected){
                    selectedPosition = index
                }
            }
            stocksDetailInfoFilterList.postValue(stocksFilteredOptionsList)
        }
    }

    fun updateList(position: Int){
        viewModelScope.launch(Dispatchers.IO) {
            stocksDetailInfoFilterList.postValue(
                stocksDetailInfoFilterList.value?.mapIndexed { index, filterOptionsData ->
                    var mFilterOptionsData = filterOptionsData
                    if (index == position) {
                        mFilterOptionsData = filterOptionsData.copy(isSelected = true)
                    }
                    if (index == selectedPosition) {
                        mFilterOptionsData = filterOptionsData.copy(isSelected = false)
                    }
                    mFilterOptionsData
                })
            selectedPosition = position
        }
    }

    fun getStocksFilteredOptionsList(): List<FilterOptionsData> {
        val filterOptionsDataList = listOf(
            FilterOptionsData(id = 0, text = "Analysis", isSelected = false),
            FilterOptionsData(id = 1, text = "News", isSelected = false),
            FilterOptionsData(id = 2, text = "Orderbook", isSelected = false),
            FilterOptionsData(id = 3, text = "Key Statistics", isSelected = true),
            FilterOptionsData(id = 4, text = "Company Profile", isSelected = false)
        )
        return filterOptionsDataList
    }

}