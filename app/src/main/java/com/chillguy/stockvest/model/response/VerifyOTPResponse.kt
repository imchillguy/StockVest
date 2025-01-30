package com.chillguy.stockvest.model.response

sealed class VerifyOTPResponse {

    data object Success: VerifyOTPResponse()

    data class Message(val message: String): VerifyOTPResponse()
}