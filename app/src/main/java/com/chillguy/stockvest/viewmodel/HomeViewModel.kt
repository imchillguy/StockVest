package com.chillguy.stockvest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.enums.StocksMomentum
import com.chillguy.stockvest.listener.IUpdateStocksInfoList
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.model.StocksInfoData
import com.chillguy.stockvest.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val stocksInfoDataList = MutableLiveData<List<StocksInfoData>>()
    val stocksOptionFilterList = MutableLiveData<List<FilterOptionsData>>()
    val stocksSymbolsList = MutableLiveData<List<String>>()
    private var selectedPosition : Int = -1
    private val repository : HomeRepository by lazy { HomeRepository() }

    init {
        fetchStocksSymbols()
    }

    private fun fetchStocksSymbols() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchStocksSymbol(stocksSymbolsList)
        }
    }

    fun fetchStocksData(stocksSymbolList: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val stocksSymbols = stocksSymbolList.joinToString(",")
            repository.fetchStocksData(stocksSymbols, object : IUpdateStocksInfoList{
                override fun update(stocksInfoData: List<StocksInfoData>) {
                    stocksInfoDataList.postValue(stocksInfoData)
                }
            })
        }
    }

    fun setFilterOptionsDataList(stocksFilteredOptionsList: List<FilterOptionsData>) {
        viewModelScope.launch(Dispatchers.IO) {
            stocksFilteredOptionsList.forEachIndexed { index, filterOptionsData ->
                if (filterOptionsData.isSelected){
                    selectedPosition = index
                }
            }
            stocksOptionFilterList.postValue(stocksFilteredOptionsList)
        }
    }

    fun updateList(position: Int){
        viewModelScope.launch(Dispatchers.IO) {
            stocksOptionFilterList.postValue(
                stocksOptionFilterList.value?.mapIndexed { index, filterOptionsData ->
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

    fun setStocksInfoData() {
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksInfoDataList = listOf(
                StocksInfoData("GoTO Pharma", "GOTO", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823865/goto_y2htpe.png", "198", "+1 (+0.53%)", StocksMomentum.BULLISH),
                StocksInfoData("Petroleum", "PTBA", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/ptba_klhfoo.png", "3.790", "-20 (-0.52%)", StocksMomentum.BEARISH),
                StocksInfoData("Ace Hardware", "ACES", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/aces_xhj1ch.png", "570", "+35 (+6.54%)", StocksMomentum.BULLISH),
                StocksInfoData("Aneka Technology", "ANTM", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/antm_gciitw.png", "1.840", "+30 (+1.66%)", StocksMomentum.BULLISH),
                StocksInfoData("Sido Industries", "SIDO", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823924/sido_flzhox.png", "740", "+25 (+3.50%)", StocksMomentum.BULLISH),
            )
            stocksInfoDataList.postValue(mStocksInfoDataList)
        }
    }

    fun getStocksFilteredOptionsList(): List<FilterOptionsData> {
        val filterOptionsDataList = listOf(
            FilterOptionsData(id = 0, text = "Trending", isSelected = true),
            FilterOptionsData(id = 1, text = "Top Gainer", isSelected = false),
            FilterOptionsData(id = 2, text = "Top Loser", isSelected = false),
            FilterOptionsData(id = 3, text = "Most Active", isSelected = false)
        )
        return filterOptionsDataList
    }

}