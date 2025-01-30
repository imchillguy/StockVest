package com.chillguy.stockvest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.enums.StocksTransactionStatus
import com.chillguy.stockvest.model.FilterOptionsData
import com.chillguy.stockvest.model.TransactionsDayData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionsViewModel: ViewModel() {

    val transactionsFilterList = MutableLiveData<List<FilterOptionsData>>()
    val transactionsDayData = MutableLiveData<List<TransactionsDayData>>()

    private var selectedPosition : Int = -1

    init {
        getTransactionsDayData()
    }

    fun setFilterOptionsDataList(transactionsFilteredOptionsList: List<FilterOptionsData>) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionsFilteredOptionsList.forEachIndexed { index, filterOptionsData ->
                if (filterOptionsData.isSelected){
                    selectedPosition = index
                }
            }
            transactionsFilterList.postValue(transactionsFilteredOptionsList)
        }
    }


    fun updateList(position: Int){
        viewModelScope.launch(Dispatchers.IO) {
            transactionsFilterList.postValue(
                transactionsFilterList.value?.mapIndexed { index, filterOptionsData ->
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

    fun getTransactionsFilteredOptionsList(): List<FilterOptionsData> {
        val filterOptionsDataList = listOf(
            FilterOptionsData(id = 0, text = "Today", isSelected = true),
            FilterOptionsData(id = 1, text = "7 Days", isSelected = false),
            FilterOptionsData(id = 2, text = "30 Days", isSelected = false),
            FilterOptionsData(id = 3, text = "90 Days", isSelected = false),
            FilterOptionsData(id = 4, text = "All Time", isSelected = false)
        )
        return filterOptionsDataList
    }

    fun getTransactionsDayData() {
        viewModelScope.launch(Dispatchers.IO) {
            val mTransactionsDayData = listOf(
                TransactionsDayData(
                    day = "Today",
                    transactionsData = listOf(
                        TransactionsDayData.TransactionsData(
                            "ANTM",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/antm_gciitw.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.OPEN
                        ),
                        TransactionsDayData.TransactionsData(
                            "SIDO",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823924/sido_flzhox.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.MATCH
                        ),
                        TransactionsDayData.TransactionsData(
                            "ACES",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/aces_xhj1ch.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.MATCH
                        ),
                        TransactionsDayData.TransactionsData(
                            "PTBA",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/ptba_klhfoo.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.WITHDRAWAl
                        )
                    )
                ),
                TransactionsDayData(
                    day = "Yesterday",
                    transactionsData = listOf(
                        TransactionsDayData.TransactionsData(
                            "ANTM",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/antm_gciitw.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.MATCH
                        ),
                        TransactionsDayData.TransactionsData(
                            "SIDO",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823924/sido_flzhox.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.MATCH
                        ),
                        TransactionsDayData.TransactionsData(
                            "PTBA",
                            "https://res.cloudinary.com/dxhfn2bfu/image/upload/v1737823864/ptba_klhfoo.png",
                            "$56",
                            "8 lot",
                            "$448",
                            StocksTransactionStatus.WITHDRAWAl
                        )
                    )
                )
            )
            transactionsDayData.postValue(mTransactionsDayData)
        }
    }

}