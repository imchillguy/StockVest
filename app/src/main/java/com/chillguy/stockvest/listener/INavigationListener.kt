package com.chillguy.stockvest.listener

import com.chillguy.stockvest.model.FragmentTransactionModel

interface INavigationListener {

    fun navigateToFragment(fragmentTransactionModel: FragmentTransactionModel)

}