package com.chillguy.stockvest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.enums.StocksMomentum
import com.chillguy.stockvest.model.StocksInfoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchListViewModel: ViewModel() {

    val stocksWatchList = MutableLiveData<List<StocksInfoData>>()

    init {
        getWatchlist()
    }

    private fun getWatchlist(){
        viewModelScope.launch(Dispatchers.IO) {
            val watchlist = listOf(
                StocksInfoData("GoTO Pharma", "GOTO", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823865/goto_y2htpe.png", "198", "+1 (+0.53%)", StocksMomentum.BULLISH),
                StocksInfoData("Petroleum", "PTBA", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/ptba_klhfoo.png", "3.790", "-20 (-0.52%)", StocksMomentum.BEARISH),
                StocksInfoData("Ace Hardware", "ACES", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/aces_xhj1ch.png", "570", "+35 (+6.54%)", StocksMomentum.BULLISH),
                StocksInfoData("Aneka Technology", "ANTM", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/antm_gciitw.png", "1.840", "+30 (+1.66%)", StocksMomentum.BULLISH),
                StocksInfoData("Sido Industries", "SIDO", "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823924/sido_flzhox.png", "740", "+25 (+3.50%)", StocksMomentum.BULLISH),
            )
            stocksWatchList.postValue(watchlist)
        }
    }

}