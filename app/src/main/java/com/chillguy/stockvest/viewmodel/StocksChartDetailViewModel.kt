package com.chillguy.stockvest.viewmodel

import android.text.SpannableString
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.api.FetchStocksOHLC
import com.chillguy.stockvest.enums.StocksMomentum
import com.chillguy.stockvest.enums.StocksPriceLotType
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.model.StocksChartDetailData
import com.chillguy.stockvest.model.StocksInfoData
import com.chillguy.stockvest.model.StocksPriceLotData
import com.chillguy.stockvest.model.response.StocksCurrentPriceResponse
import com.chillguy.stockvest.retrofit.TwelveDataApiService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.random.Random

class StocksChartDetailViewModel: ViewModel() {

    val stocksSymbol = MutableLiveData<String>()
    val stocksPrice = MutableLiveData<String>()
    val stocksPriceChange = MutableLiveData<SpannableString>()
    val stocksChartTimeFrameFilterList = MutableLiveData<List<FilterOptionsData>>()
    val stocksChartData = MutableLiveData<MutableList<Pair<String, Float>>>()
    val stocksChartDetailData = MutableLiveData<StocksInfoData>()

    private var selectedPosition: Int = -1

    init {
        getStocksChartPriceOHLCData()
    }

    fun getStocksDetailsData(): StocksChartDetailData{

        val stocksChartsCoordinates = mutableListOf<Pair<String, Float>>()

        for (i in 1..75) {
            val coordinate = 5 + Random.nextFloat()*2
            stocksChartsCoordinates.add(i.toString() to coordinate)
        }

        val stocksPriceLotData = listOf(
            StocksPriceLotData("Open", "7.091,76", StocksPriceLotType.BEARISH),
            StocksPriceLotData("Lot", "186,26M"),
            StocksPriceLotData("High", "7.100,81", StocksPriceLotType.BULLISH),
            StocksPriceLotData("Value", "9,88T"),
            StocksPriceLotData("Low", "7.016,70", StocksPriceLotType.BEARISH),
            StocksPriceLotData("Freq", "1,10M")
        )

        val stocksChartDetailData = StocksChartDetailData(
            stocksInfoData = StocksInfoData(
                stocksSymbol = "BTC/USD",
                stocksName = "",
                stocksIconUrl = "",
                stocksPrice = "0.0",
                stocksPriceChange = "-35.72 (-0.50%)",
                stocksMomentum = StocksMomentum.BULLISH
            ),
            stocksCategory = listOf(),
            stocksChartData = stocksChartsCoordinates,
            stocksPriceLotData = stocksPriceLotData,
            stocksTimeFrameData = listOf()
        )
        return stocksChartDetailData
    }

    fun updateStocksPriceChart(stocksPriceData: String){
        viewModelScope.launch(Dispatchers.IO) {
            val stocksCurrentPriceData =
                Gson().fromJson<StocksCurrentPriceResponse>(stocksPriceData, StocksCurrentPriceResponse::class.java)
            stocksPrice.postValue(stocksCurrentPriceData.price.toString())
            if (stocksChartData.value == null) {
                async(Dispatchers.IO) { getStocksChartPriceOHLCData() }.await()
            }
            stocksChartData.value?.let { list ->
                val size = list.size
                Log.i("TAG", "updateStocksPriceChart: $size")
                list.add("${size}_c" to stocksCurrentPriceData.price.toFloat())
                stocksChartData.postValue(list)
            }
        }
    }

    private fun getStocksChartPriceOHLCData() {
        viewModelScope.launch(Dispatchers.IO) {
            async(Dispatchers.IO) {
                val fetchStocksPriceOHLC = TwelveDataApiService.createService(FetchStocksOHLC::class.java)
                val stocksChartOHLCPriceData = fetchStocksPriceOHLC.fetchStocksOHLC(
                    symbol = "BTC/USD"
                )
                val chartData = mutableListOf<Pair<String, Float>>()
                stocksChartOHLCPriceData.values.forEachIndexed { index, ohlcData ->
                    chartData.add("${index}_h" to ohlcData.high.toFloat())
                    chartData.add("${index}_l" to ohlcData.low.toFloat())
                }
                stocksChartData.postValue(chartData)
            }.await()
        }
    }

    fun getStocksChartDetailsData(): StocksChartDetailData{

        val stocksChartsCoordinates = mutableListOf<Pair<String, Float>>()

        for (i in 1..75) {
            val coordinate = 5 + Random.nextFloat()*2
            stocksChartsCoordinates.add(i.toString() to coordinate)
        }

        val stocksChartDetailData = StocksChartDetailData(
            stocksInfoData = StocksInfoData(
                stocksSymbol = "ACES",
                stocksName = "Ace Hardware",
                stocksIconUrl = "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/aces_xhj1ch.png",
                stocksPrice = "7.056,04",
                stocksPriceChange = "-35.72 (-0.50%)",
                stocksMomentum = StocksMomentum.BEARISH
            ),
            stocksCategory = listOf("ESGQKEHATI", "Bank", "INFOBANK15", "IDXSMC-COM"),
            stocksChartData = stocksChartsCoordinates,
            stocksPriceLotData = listOf(),
            stocksTimeFrameData = getStocksChartTimeFrameFilterList()
        )
        return stocksChartDetailData
    }

    fun setFilterOptionsDataList(stocksFilteredOptionsList: List<FilterOptionsData>) {
        viewModelScope.launch(Dispatchers.IO) {
            stocksFilteredOptionsList.forEachIndexed { index, filterOptionsData ->
                if (filterOptionsData.isSelected){
                    selectedPosition = index
                }
            }
            stocksChartTimeFrameFilterList.postValue(stocksFilteredOptionsList)
        }
    }

    fun updateList(position: Int){
        viewModelScope.launch(Dispatchers.IO) {
            stocksChartTimeFrameFilterList.postValue(
                stocksChartTimeFrameFilterList.value?.mapIndexed { index, filterOptionsData ->
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

    private fun getStocksChartTimeFrameFilterList(): List<FilterOptionsData> {
        val filterOptionsDataList = listOf(
            FilterOptionsData(id = 0, text = "1D", isSelected = false),
            FilterOptionsData(id = 1, text = "5D", isSelected = false),
            FilterOptionsData(id = 2, text = "1M", isSelected = true),
            FilterOptionsData(id = 3, text = "6M", isSelected = false),
            FilterOptionsData(id = 4, text = "YTD", isSelected = false),
            FilterOptionsData(id = 5, text = "1Y", isSelected = false),
            FilterOptionsData(id = 6, text = "3Y", isSelected = false),
            FilterOptionsData(id = 7, text = "5Y", isSelected = false)
        )
        return filterOptionsDataList
    }

    fun updateChartWithTimeFrame() {
        viewModelScope.launch(Dispatchers.IO) {
            val stocksChartsCoordinates = mutableListOf<Pair<String, Float>>()

            for (i in 1..75) {
                val coordinate = 5 + Random.nextFloat()*2
                stocksChartsCoordinates.add(i.toString() to coordinate)
            }
            stocksChartData.postValue(stocksChartsCoordinates)
        }
    }

}