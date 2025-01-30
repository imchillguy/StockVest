package com.chillguy.stockvest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.model.response.LoginResponse
import com.chillguy.stockvest.utils.Utils.isValidEmail
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val loginResponse = MutableLiveData<LoginResponse>()

    private val auth : FirebaseAuth by lazy { Firebase.auth }

    fun isValidEmail() = email.value?.isValidEmail() ?: false

    fun loginUser(){
        loginResponse.value = LoginResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email.value?:"", password.value?:"")
                .addOnSuccessListener {
                    loginResponse.postValue(LoginResponse.Success)
                }
                .addOnFailureListener { error ->
                    loginResponse.postValue(LoginResponse.Error(error.message?:""))
                }
        }
    }

}