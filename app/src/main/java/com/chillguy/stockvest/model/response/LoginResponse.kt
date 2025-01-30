package com.chillguy.stockvest.model.response

sealed class LoginResponse {

    data object Loading : LoginResponse()

    data object Success: LoginResponse()

    data class Error(val errorMsg: String): LoginResponse()
}