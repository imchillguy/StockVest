package com.chillguy.stockvest.utils

import android.content.Context
import android.util.Patterns
import android.util.TypedValue

object Utils {

    fun String.stringContainsNumber(): Boolean {
        for (c in this) {
            if (c in '0'..'9') {
                return true
            }
        }
        return false
    }

    fun String.isValidEmail(): Boolean {
        val matcher = Patterns.EMAIL_ADDRESS.matcher(this)
        return matcher.matches()
    }

    fun String.isValidPhoneNumber(): Boolean {
        val matcher = Patterns.PHONE.matcher(this)
        return matcher.matches()
    }

    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )

}