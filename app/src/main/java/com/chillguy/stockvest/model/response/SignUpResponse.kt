package com.chillguy.stockvest.model.response

sealed class SignUpResponse {

    object Loading : SignUpResponse()

    data class Success(val successMsg: String): SignUpResponse()

    data class Error(val errorMsg: String): SignUpResponse()

}