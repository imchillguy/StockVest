package com.chillguy.stockvest.extension

import android.text.Editable

object StringExtension {

    fun String.toEditable() : Editable =
        Editable.Factory.getInstance().newEditable(this)
}