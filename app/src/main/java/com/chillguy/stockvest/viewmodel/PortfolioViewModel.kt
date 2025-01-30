package com.chillguy.stockvest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.model.StocksInvestmentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PortfolioViewModel: ViewModel() {

    val stocksInvestmentData = MutableLiveData<List<StocksInvestmentData>>()

    init {
        getStocksInvestmentData()
    }

    private fun getStocksInvestmentData() {
        viewModelScope.launch(Dispatchers.IO) {
            val mStocksInvestmentData = listOf(
                StocksInvestmentData("GoTO Pharma", "GOTO", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823865/goto_y2htpe.png", "$198", "+1 (+0.53%)","26 Lot (25.32%)"),
                StocksInvestmentData("Petroleum", "PTBA", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/ptba_klhfoo.png", "3.790", "-20 (-0.52%)", "12 Lot (9.7%)"),
                StocksInvestmentData("Ace Hardware", "ACES", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/aces_xhj1ch.png", "570", "+35 (+6.54%)", "8 Lot (25.32%)"),
                StocksInvestmentData("Aneka Technology", "ANTM", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/antm_gciitw.png", "1.840", "+30 (+1.66%)", "53 Lot (17.09%)"),
                StocksInvestmentData("Sido Industries", "SIDO", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823924/sido_flzhox.png", "740", "+25 (+3.50%)", "34 Lot (18.3%)"),
            )
            stocksInvestmentData.postValue(mStocksInvestmentData)
        }
    }

}