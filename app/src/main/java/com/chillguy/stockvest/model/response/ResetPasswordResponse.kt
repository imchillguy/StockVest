package com.chillguy.stockvest.model.response

sealed class ResetPasswordResponse {

    data object Success: ResetPasswordResponse()

    data object Error: ResetPasswordResponse()

}