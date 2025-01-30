package com.chillguy.stockvest.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object VIewModelFactory {

    fun <T: ViewModel> T.createFactory(): ViewModelProvider.Factory {
        val viewModel = this
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return viewModel as T
            }
        }
    }

}