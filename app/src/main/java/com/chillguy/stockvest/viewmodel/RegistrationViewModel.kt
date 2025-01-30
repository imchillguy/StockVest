package com.chillguy.stockvest.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chillguy.stockvest.R
import com.chillguy.stockvest.StockVestApplication
import com.chillguy.stockvest.livedata.SingleMutableLiveData
import com.chillguy.stockvest.model.response.ResetPasswordResponse
import com.chillguy.stockvest.model.response.SignUpResponse
import com.chillguy.stockvest.model.response.VerifyOTPResponse
import com.chillguy.stockvest.utils.Utils.isValidEmail
import com.chillguy.stockvest.utils.Utils.isValidPhoneNumber
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RegistrationViewModel: ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()

    val otpBox1 = MutableLiveData<String>()
    val otpBox2 = MutableLiveData<String>()
    val otpBox3 = MutableLiveData<String>()
    val otpBox4 = MutableLiveData<String>()
    private var verificationId: String?= null
    private var resendToken: PhoneAuthProvider. ForceResendingToken?= null

    val signUpResponse = SingleMutableLiveData<SignUpResponse>()
    val verifyOTPResponse = SingleMutableLiveData<VerifyOTPResponse>()
    val resendPasswordResponse = SingleMutableLiveData<ResetPasswordResponse>()

    private val auth : FirebaseAuth by lazy { Firebase.auth }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
        }

        override fun onVerificationFailed(e: FirebaseException) {
            verifyOTPResponse.postValue(VerifyOTPResponse.Message(StockVestApplication.getApplicationContext().getString(R.string.otp_verification_failed)))
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            verificationId = p0
            resendToken = p1
            verifyOTPResponse.postValue(VerifyOTPResponse.Message(StockVestApplication.getApplicationContext().getString(R.string.otp_sent_successfully)))
        }

    }

    private var phoneAuthOptionsBuilder: PhoneAuthOptions.Builder? = null


    fun isValidEmail() = email.value?.isValidEmail() ?: false

    fun isValidPhoneNumber() = phoneNumber.value?.isValidPhoneNumber() ?: false

    private fun getOTP(): String{
        return "${otpBox1.value?:""}${otpBox2.value?:""}${otpBox3.value?:""}${otpBox4.value?:""}"
    }

    fun isValidOTP() = getOTP().length == 4

    fun passwordMatches(): Boolean {
        return (!password.value.isNullOrEmpty()
                && !confirmPassword.value.isNullOrEmpty()
                && password.value == confirmPassword.value)
    }

    fun createNewUser() {
        signUpResponse.setValue(SignUpResponse.Loading)
        phoneNumber.value = ""
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email.value?:"", password.value?:"")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        signUpResponse.postValue(SignUpResponse.Success("User created Successfully"))
                    } else {
                        signUpResponse.postValue(SignUpResponse.Error("Please try again"))
                    }
                }
        }
    }

    fun initPhoneAuthOption(activity: FragmentActivity){
        viewModelScope.launch(Dispatchers.IO) {
             phoneAuthOptionsBuilder = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91${phoneNumber.value?:""}")
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callbacks)
        }
    }

    fun sendOTPOnPhoneNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            phoneAuthOptionsBuilder?.let { mPhoneAuthOptionsBuilder ->
                PhoneAuthProvider.verifyPhoneNumber(mPhoneAuthOptionsBuilder.build())
            }
        }
    }

    fun resendOTPOnPhoneNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            if (phoneAuthOptionsBuilder != null && resendToken != null) {
                PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptionsBuilder!!.setForceResendingToken(resendToken!!).build())
            }
        }
    }

    fun verifyOTP() {
        // Added this as firebase sms requires billing account
        verifyOTPResponse.postValue(VerifyOTPResponse.Success)
//        viewModelScope.launch(Dispatchers.IO) {
//            if (!verificationId.isNullOrEmpty()) {
//                val credential = PhoneAuthProvider.getCredential(verificationId!!, getOTP())
//                auth.signInWithCredential(credential)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            verifyOTPResponse.postValue(VerifyOTPResponse.Success)
//                        } else {
//                            verifyOTPResponse.postValue(VerifyOTPResponse.Message(StockVestApplication.getApplicationContext().getString(R.string.otp_verification_failed)))
//                        }
//                    }
//            }
//        }
    }

    fun sendPasswordResetEmail(email: String){
        viewModelScope.launch(Dispatchers.IO) {
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    resendPasswordResponse.postValue(ResetPasswordResponse.Success)
                }
                .addOnFailureListener {
                    resendPasswordResponse.postValue(ResetPasswordResponse.Error)
                }
        }
    }

    fun resendPasswordResetEmail(email: String){
        viewModelScope.launch(Dispatchers.IO) {
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener {

                }
                .addOnFailureListener {
                }
        }
    }

}