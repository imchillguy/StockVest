package com.chillguy.stockvest.viewmodel.bottomsheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.R
import com.chillguy.stockvest.StockVestApplication
import com.chillguy.stockvest.model.OrderConfirmationDetails
import com.chillguy.stockvest.model.TableData
import com.chillguy.stockvest.repository.StocksBuyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StocksBuyViewModel(
    val stocksBuyRepository: StocksBuyRepository
): ViewModel() {

    val stocksBuyingPrice= MutableLiveData<List<TableData>>()
    val stocksBuyOrderLot = MutableLiveData<Int>(0)
    val stocksBuyOrderLotInPercentage = MutableLiveData<Int>(0)
    private val stocksBuyPrice = MutableLiveData<Double>(0.0)
    val stocksMarketPrice = MutableLiveData<String>()
    val stocksBuyInvestmentAndFee = MutableLiveData<Double>(0.0)
    private val stocksBuyingPower = MutableLiveData<Double>(1000.0)
    val buyButtonEnable = MutableLiveData<Boolean>(false)

    init {
        getStocksBuyingPrice()
        getStocksMarketPrice()
    }

    private fun getStocksMarketPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            stocksMarketPrice.postValue("5.70")
        }
    }

    private fun getStocksBuyingPrice(){
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksBuyingPrice = stocksBuyRepository.getStocksBuyingPrice()
            stocksBuyingPrice.postValue(mStocksBuyingPrice)
        }
    }

    fun setStocksBuyOrderLot(value: String?) {
        val lot = if (value.isNullOrEmpty()) 0 else value.toInt()
        stocksBuyOrderLot.value = lot
    }

    fun setStocksBuyPrice(value: String?) {
        val price = if (value.isNullOrEmpty()) 0.0 else value.toDouble()
        stocksBuyPrice.value = price
    }

    fun setButtonStateAndInvestmentFee() {
        val stocksLotBuyPrice = calculateStocksLotBuyPrice()
        buyButtonEnable.value =
            stocksLotBuyPrice > 0  && stocksLotBuyPrice < (stocksBuyingPower.value?:0.0)
        val investmentAndFee = String.format("%.4f",stocksLotBuyPrice).toDouble()
        stocksBuyInvestmentAndFee.value = investmentAndFee
    }

    private fun calculateStocksLotBuyPrice()= 1.12 * ((stocksBuyOrderLot.value?:0) * (stocksBuyPrice.value?:0.0))

    fun stocksBuyingPowerWithPercentage(value: Double) {
        val marketPrice = stocksMarketPrice.value?.toDouble() ?: 0.0
        if (marketPrice != 0.0) {
            val totalLots = (stocksBuyingPower.value?.times(value))?.div(1.12 * marketPrice)
            stocksBuyOrderLotInPercentage.value = totalLots?.toInt() ?: 0
        }
    }

    fun buyStocks() {
        //Log.i("StocksBuyViewModel", "buyStocks: ${stocksBuyOrderLot}")
    }

    fun getOrderConfirmationDetails(): OrderConfirmationDetails {
        val orderConfirmationDetails =  OrderConfirmationDetails(
                title = StockVestApplication.getApplicationContext().getString(R.string.order_confirmation),
                stocksSymbol = "ACES",
                stocksName = "Ace Hardware",
                stocksImageUrl = "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/aces_xhj1ch.png",
            )
        return orderConfirmationDetails
    }
}