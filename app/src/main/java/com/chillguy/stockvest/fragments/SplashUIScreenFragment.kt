package com.chillguy.stockvest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chillguy.stockvest.R
import com.chillguy.stockvest.databinding.FragmentSplashUiScreenBinding
import com.chillguy.stockvest.listener.INavigationListener
import com.chillguy.stockvest.model.FragmentTransactionModel

class SplashUIScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashUiScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_ui_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            startedTv.setOnClickListener {
                val fragmentTransactionModel = FragmentTransactionModel(
                    fragment = LoginFragment::class.java
                )
                (activity as? INavigationListener)?.navigateToFragment(fragmentTransactionModel)
            }
        }
    }
}