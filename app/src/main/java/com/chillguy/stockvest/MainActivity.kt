package com.chillguy.stockvest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chillguy.stockvest.enums.FragmentTransactionType
import com.chillguy.stockvest.fragments.SplashUIScreenFragment
import com.chillguy.stockvest.factory.FragmentInstanceFactory
import com.chillguy.stockvest.fragments.MainFragment
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity(), INavigationListener {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (auth.currentUser != null) {
            navigateToMainFragment()
        } else {
            showSplashUIScreen()
        }
    }

    private fun showSplashUIScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, SplashUIScreenFragment())
            .commit()
    }

    private fun navigateToMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, MainFragment())
            .commit()
    }

    override fun navigateToFragment(fragmentTransactionModel: FragmentTransactionModel) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentInstance = FragmentInstanceFactory.getInstance<Fragment>(fragmentTransactionModel.fragment.simpleName, fragmentTransactionModel.bundle)
        when (fragmentTransactionModel.fragmentTransactionType) {
            FragmentTransactionType.ADD -> {
                fragmentTransaction.add(R.id.frame_layout, fragmentInstance)
            }
            FragmentTransactionType.REPLACE -> {
                fragmentTransaction.replace(R.id.frame_layout, fragmentInstance)
            }
        }
        if(fragmentTransactionModel.addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTransactionModel.tag)
        }
        fragmentTransaction.commit()
    }
}