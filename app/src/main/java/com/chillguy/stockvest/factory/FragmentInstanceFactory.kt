package com.chillguy.stockvest.factory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.chillguy.stockvest.fragments.EnterPhoneNumberFragment
import com.chillguy.stockvest.fragments.ForgotPasswordFragment
import com.chillguy.stockvest.fragments.LoginFragment
import com.chillguy.stockvest.fragments.MainFragment
import com.chillguy.stockvest.fragments.RegistrationSuccessFragment
import com.chillguy.stockvest.fragments.SignUpFragment
import com.chillguy.stockvest.fragments.StocksDetailInfoFragment
import com.chillguy.stockvest.fragments.ValidatePhoneNumberFragment

object FragmentInstanceFactory {

    @Suppress("UNCHECKED_CAST")
    fun <T: Fragment> getInstance(className: String, bundle: Bundle?): T {
        Log.i("TAG", "getInstance: $className")
        return when (className) {
            LoginFragment::class.java.simpleName -> LoginFragment() as T
            SignUpFragment::class.java.simpleName -> SignUpFragment() as T
            EnterPhoneNumberFragment::class.java.simpleName -> EnterPhoneNumberFragment() as T
            ValidatePhoneNumberFragment::class.java.simpleName -> ValidatePhoneNumberFragment() as T
            RegistrationSuccessFragment::class.java.simpleName -> RegistrationSuccessFragment() as T
            ForgotPasswordFragment::class.java.simpleName -> ForgotPasswordFragment() as T
            StocksDetailInfoFragment::class.java.simpleName -> StocksDetailInfoFragment.newInstance(bundle) as T
            MainFragment::class.java.simpleName -> MainFragment() as T
            else -> throw UnsupportedOperationException("No Fragments Found")
        }
    }

}