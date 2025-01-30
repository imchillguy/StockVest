package com.chillguy.stockvest.model

import android.os.Bundle
import com.chillguy.stockvest.enums.FragmentTransactionType

data class FragmentTransactionModel(
    val fragment: Class<*>,
    val fragmentTransactionType: FragmentTransactionType = FragmentTransactionType.ADD,
    val addToBackStack: Boolean = false,
    val tag: String = "",
    val bundle: Bundle? = null
)
