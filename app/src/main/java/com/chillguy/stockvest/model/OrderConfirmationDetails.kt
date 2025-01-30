package com.chillguy.stockvest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderConfirmationDetails(
    val title: String,
    val stocksSymbol: String,
    val stocksName: String,
    val stocksImageUrl: String
): Parcelable